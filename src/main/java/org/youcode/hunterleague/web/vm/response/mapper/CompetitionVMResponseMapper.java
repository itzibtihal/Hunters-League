package org.youcode.hunterleague.web.vm.response.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.youcode.hunterleague.domain.Competition;
import org.youcode.hunterleague.web.vm.response.CompetitionVMResponse;

@Mapper(componentModel = "spring")
public interface CompetitionVMResponseMapper {

    CompetitionVMResponseMapper INSTANCE = Mappers.getMapper(CompetitionVMResponseMapper.class);
    //CompetitionVMResponse toVMResponse(Competition competition);

    public default CompetitionVMResponse toVMResponse(Competition competition) {
        if (competition == null) {
            return null;
        }
        return CompetitionVMResponse.builder()
                .id(competition.getId())
                .code(competition.getCode())
                .location(competition.getLocation())
                .date(competition.getDate())
                .speciesType(competition.getSpeciesType())
                .minParticipants(competition.getMinParticipants())
                .maxParticipants(competition.getMaxParticipants())
                .openRegistration(competition.getOpenRegistration())
                .participantCount(competition.getParticipantCount())
                .build();
    }

}
