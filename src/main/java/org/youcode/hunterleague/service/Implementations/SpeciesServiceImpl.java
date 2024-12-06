package org.youcode.hunterleague.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.entities.Species;
import org.youcode.hunterleague.repository.SpeciesRepository;
import org.youcode.hunterleague.service.SpeciesService;
import org.youcode.hunterleague.web.exception.species.InvalidSpeciesException;
import org.youcode.hunterleague.web.exception.species.SpeciesNotFoundException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {

    private SpeciesRepository speciesRepository;

    @Override
    public Species createSpecies(Species species) {
        if (species == null) {
            throw new InvalidSpeciesException("Species cannot be null");
        }
        return speciesRepository.save(species);
    }

    @Override
    public Page<Species> getAllSpecies(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return speciesRepository.findAll(pageable);
    }

    @Override
    public void delete(UUID id){
        Species speciesToDelete = speciesRepository.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("species not found"));

        speciesRepository.delete(speciesToDelete);
    }

    @Override
    public Species updateSpecies(UUID id, Species species) {
        Species speciesToUpdate = speciesRepository.findById(id)
                .orElseThrow(() -> new SpeciesNotFoundException("Species not found"));

        speciesToUpdate.setName(species.getName() != null ? species.getName() : speciesToUpdate.getName());
        speciesToUpdate.setCategory(species.getCategory() != null ? species.getCategory() : speciesToUpdate.getCategory());
        speciesToUpdate.setMinimumWeight(species.getMinimumWeight() != null ? species.getMinimumWeight() : speciesToUpdate.getMinimumWeight());
        speciesToUpdate.setDifficulty(species.getDifficulty() != null ? species.getDifficulty() : speciesToUpdate.getDifficulty());
        speciesToUpdate.setPoints(species.getPoints() != null ? species.getPoints() : speciesToUpdate.getPoints());

        return speciesRepository.save(speciesToUpdate);
    }

    @Override
    public Species findById(UUID id) {
        return speciesRepository.findById(id).orElseThrow(()-> new SpeciesNotFoundException("species not found"));
    }
}
