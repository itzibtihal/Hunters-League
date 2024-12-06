package org.youcode.hunterleague.service.DTOs.mapper;

import org.mapstruct.Mapper;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.service.DTOs.CompetitionDTO;

@Mapper(componentModel = "spring")
public interface CompetitionDTOMapper {
    CompetitionDTO toCompetitionDTO(Competition competition);
    Competition toCompetition (CompetitionDTO competitionDTO);
}
