package org.youcode.hunterleague.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.domain.enums.SpeciesType;

import java.util.UUID;

public interface SpeciesService {
    Page<Species> getSpeciesByCategory(SpeciesType category, int page, int size);
    Species saveSpecies(Species species);
    void deleteSpecies(UUID id);

}
