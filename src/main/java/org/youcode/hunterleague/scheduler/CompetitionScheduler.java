package org.youcode.hunterleague.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.youcode.hunterleague.service.CompetitionService;

@Component
public class CompetitionScheduler {

    private final CompetitionService competitionService;

    public CompetitionScheduler(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void closeRegistrationsBeforeCompetition() {
        competitionService.closeRegistrationsBeforeCompetition();
    }
}
