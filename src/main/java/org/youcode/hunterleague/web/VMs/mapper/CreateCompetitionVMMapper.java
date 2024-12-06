package org.youcode.hunterleague.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.web.VMs.CompetitionVMs.CreateCompetitionVM;

@Mapper(componentModel = "spring")
public interface CreateCompetitionVMMapper {
    Competition toCompetition(CreateCompetitionVM createCompetitionVM);
    CreateCompetitionVM toCreateCompetitionVM(Competition competition);
}
