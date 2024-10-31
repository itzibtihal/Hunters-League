package org.youcode.hunterleague.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.hunterleague.domain.Competition;

import java.util.UUID;

public interface CompetitionService {

    Competition createCompetition(Competition competition);

    void deleteCompetition(UUID id);

    Competition updateCompetition(UUID id, @Valid Competition competitionDetails);

    Competition getCompetitionDetails(UUID id);

    Page<Competition> getAllCompetitions(Pageable pageable);

    Page<Competition> getCompetitionsByLocation(String location, int page, int size);

    Page<Competition> getCompetitionsByOpenRegistration(boolean openRegistration, int page, int size);

}
