package org.youcode.hunterleague.web.vm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.web.vm.SpeciesVM;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {

    SpeciesMapper INSTANCE = Mappers.getMapper(SpeciesMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "minimumWeight", source = "minimumWeight")
    @Mapping(target = "difficulty", source = "difficulty")
    @Mapping(target = "points", source = "points")
    Species toEntity(SpeciesVM SpeciesVM);
    SpeciesMapper toDto(Species species);

}
