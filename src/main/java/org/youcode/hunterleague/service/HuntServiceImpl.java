package org.youcode.hunterleague.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.hunterleague.domain.Hunt;
import org.youcode.hunterleague.domain.Participation;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.domain.enums.SpeciesType;
import org.youcode.hunterleague.exception.hunt.HuntSaveException;
import org.youcode.hunterleague.repository.HuntRepository;
import org.youcode.hunterleague.repository.ParticipationRepository;
import org.youcode.hunterleague.repository.SpeciesRepository;

import java.util.UUID;

@Service
public class HuntServiceImpl {

    private final HuntRepository huntRepository;
    private final SpeciesRepository speciesRepository;
    private final ParticipationRepository participationRepository;

    @Autowired
    public HuntServiceImpl(HuntRepository huntRepository, SpeciesRepository speciesRepository, ParticipationRepository participationRepository) {
        this.huntRepository = huntRepository;
        this.speciesRepository = speciesRepository;
        this.participationRepository = participationRepository;
    }

    @Transactional
    public Hunt saveHunt(@Valid @NotNull Hunt hunt) {
        UUID speciesId = hunt.getSpecies().getId();
        Species species = speciesRepository.findById(speciesId)
                .orElseThrow(() -> new HuntSaveException("Species not found"));
        hunt.setSpecies(species);

        UUID participationId = hunt.getParticipation().getId();
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new HuntSaveException("Participation not found"));
        hunt.setParticipation(participation);

        validateHunt(hunt);

        try {
            Hunt savedHunt = huntRepository.save(hunt);
            updateParticipationScore(savedHunt);
            return savedHunt;
        } catch (Exception e) {
            throw new HuntSaveException("Failed to save hunt: " + e.getMessage());
        }
    }

    private void validateHunt(Hunt hunt) {
        if (hunt.getParticipation() == null) {
            throw new HuntSaveException("Participation must not be null");
        }
        if (hunt.getParticipation().getUser() == null) {
            throw new HuntSaveException("User in Participation must not be null");
        }
        if (hunt.getParticipation().getCompetition() == null) {
            throw new HuntSaveException("Competition in Participation must not be null");
        }
        if (hunt.getWeight() == null) {
            throw new HuntSaveException("Weight must not be null");
        }
        if (hunt.getSpecies() == null || hunt.getSpecies().getMinimumWeight() == null) {
            throw new HuntSaveException("Species and its minimum weight must not be null");
        }
        if (hunt.getWeight() < hunt.getSpecies().getMinimumWeight()) {
            throw new HuntSaveException("The weight of the hunt is less than the minimum weight, we're not counting it");
        }
    }

    private void updateParticipationScore(Hunt hunt) {
        Participation participation = hunt.getParticipation();
        if (participation.getUser() == null || participation.getCompetition() == null) {
            throw new HuntSaveException("User or Competition in Participation is null");
        }
        System.out.println(participation.getUser().getId());
        System.out.println(participation.getCompetition().getId());
        double currentScore = participation.getScore() != null ? participation.getScore() : 0.0;
        double weight = hunt.getWeight();
        SpeciesType speciesType = hunt.getSpecies().getCategory();
        double speciesTypeValue = speciesType.getValue();
        double difficultyFactor = hunt.getSpecies().getDifficulty().getValue();

        double newScore = currentScore + (weight * speciesTypeValue) * difficultyFactor;
        participation.setScore(newScore);

        participationRepository.save(participation);
    }
}