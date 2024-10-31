package org.youcode.hunterleague.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.domain.enums.SpeciesType;
import org.youcode.hunterleague.repository.SpeciesRepository;
import org.youcode.hunterleague.service.SpeciesService;

import java.util.UUID;

@Service
public class SpeciesServiceImpl implements SpeciesService {

    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesServiceImpl(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @Override
    public Page<Species> getSpeciesByCategory(SpeciesType category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return speciesRepository.findByCategory(category, pageable);
    }

    @Override
    public Species saveSpecies(Species species) {
        return speciesRepository.save(species);
    }

    @Override
    public void deleteSpecies(UUID id) {
        speciesRepository.deleteById(id);
    }

}

