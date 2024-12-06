package org.youcode.hunterleague.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.web.VMs.CompetitionVMs.UpdateCompetitionVM;

@Mapper(componentModel = "spring")
public interface UpdateCompetitionVMMapper {
    Competition toCompetition(UpdateCompetitionVM updateCompetitionVM);
    UpdateCompetitionVM toUpdateCompetitionVM(Competition competition);
}
