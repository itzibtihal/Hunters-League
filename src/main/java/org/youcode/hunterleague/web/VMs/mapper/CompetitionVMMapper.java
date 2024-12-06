package org.youcode.hunterleague.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.web.VMs.CompetitionVMs.CompetitionVM;

@Mapper(componentModel = "spring")
public interface CompetitionVMMapper {
    Competition toCompetition(CompetitionVM competitionVM);
    CompetitionVM toCompetitionVM(Competition competition);
}
