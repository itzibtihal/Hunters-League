package org.youcode.hunterleague.web.vm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.youcode.hunterleague.domain.Hunt;
import org.youcode.hunterleague.web.vm.HuntVM;

@Mapper
public interface HuntMapper {
    HuntMapper INSTANCE = Mappers.getMapper(HuntMapper.class);

    @Mapping(source = "speciesId", target = "species.id")
    @Mapping(source = "participationId", target = "participation.id")
    Hunt toEntity(HuntVM huntVM);

    @Mapping(source = "species.id", target = "speciesId")
    @Mapping(source = "participation.id", target = "participationId")
    HuntVM toVM(Hunt hunt);
}