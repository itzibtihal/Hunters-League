package org.youcode.hunterleague.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.hunterleague.domain.Hunt;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.exception.hunt.HuntSaveException;
import org.youcode.hunterleague.repository.HuntRepository;
import org.youcode.hunterleague.repository.SpeciesRepository;

import java.util.UUID;

@Service
public class HuntServiceImpl {

    private final HuntRepository huntRepository;
    private final SpeciesRepository speciesRepository;

    @Autowired
    public HuntServiceImpl(HuntRepository huntRepository, SpeciesRepository speciesRepository) {
        this.huntRepository = huntRepository;
        this.speciesRepository = speciesRepository;
    }

    @Transactional
    public Hunt saveHunt(@Valid @NotNull Hunt hunt) {
        UUID speciesId = hunt.getSpecies().getId();
        Species species = speciesRepository.findById(speciesId)
                .orElseThrow(() -> new HuntSaveException("Species not found"));
        hunt.setSpecies(species);
        System.out.println(species.getMinimumWeight());
        validateHunt(hunt);
        try {
            return huntRepository.save(hunt);
        } catch (Exception e) {
            throw new HuntSaveException("Failed to save hunt: " + e.getMessage());
        }    }


    private void validateHunt(Hunt hunt) {
        if (hunt.getParticipation() == null) {
            throw new HuntSaveException("Participation must not be null");
        }
        if (hunt.getWeight() == null || hunt.getWeight() > 20) {
            throw new HuntSaveException("Weight must not be null and must be less than or equal to 20");
        }
        if (hunt.getSpecies() == null || hunt.getSpecies().getMinimumWeight() == null) {
            throw new HuntSaveException("Species and its minimum weight must not be null");
        }
        if (hunt.getWeight() < hunt.getSpecies().getMinimumWeight()) {
            throw new HuntSaveException("The weight of the hunt is less than the minimum weight, we're not counting it");
        }
    }
}