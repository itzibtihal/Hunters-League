package org.youcode.hunterleague.web.vm.response.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.web.vm.response.SpeciesVMResponse;

@Mapper(componentModel = "spring")
public interface SpeciesVMResponseMapper {

    SpeciesVMResponseMapper INSTANCE = Mappers.getMapper(SpeciesVMResponseMapper.class);

//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "category", source = "category")
//    @Mapping(target = "minimumWeight", source = "minimumWeight")
//    @Mapping(target = "difficulty", source = "difficulty")

    SpeciesVMResponse toDto(Species species);
}
