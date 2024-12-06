package org.youcode.hunterleague.service;

import org.springframework.data.domain.Page;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.service.DTOs.CompetitionDTO;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionService {

    Competition createCompetition(Competition competition);
    Optional<Competition> findByCode(String code);
    Competition findById(UUID id);
    Page<Competition> findAllCompetitionsPaginated(int page, int size);
    Boolean delete(UUID id);
    Competition update(UUID id, Competition competition);
    CompetitionDTO getCompetitionDetails(UUID id);
    void closeRegistrationsBeforeCompetition();
}
