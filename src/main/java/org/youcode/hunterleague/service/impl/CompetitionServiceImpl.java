package org.youcode.hunterleague.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.Competition;
import org.youcode.hunterleague.repository.CompetitionRepository;
import org.youcode.hunterleague.service.CompetitionService;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;


    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;

    }


    @Override
    public Competition createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

}
