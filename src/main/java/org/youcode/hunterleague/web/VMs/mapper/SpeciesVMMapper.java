package org.youcode.hunterleague.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.hunterleague.domain.entities.Species;
import org.youcode.hunterleague.web.VMs.SpeciesVMs.SpeciesVM;

@Mapper(componentModel = "spring")
public interface SpeciesVMMapper {

    Species toSpecies(SpeciesVM speciesVM);
    SpeciesVM toSpeciesVM(Species species);
}
