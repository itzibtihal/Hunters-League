package org.youcode.hunterleague.config.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.youcode.hunterleague.domain.Competition;
import org.youcode.hunterleague.repository.CompetitionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CompetitionScheduler {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionScheduler(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Scheduled(fixedRate = 3600000)
    public void closeRegistrations() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.plusHours(24);

        List<Competition> competitions = competitionRepository.findAll();
        for (Competition competition : competitions) {
            if (competition.getDate().isBefore(threshold) && Boolean.TRUE.equals(competition.getOpenRegistration())) {
                competition.setOpenRegistration(false);
                competitionRepository.save(competition);
            }
        }
    }
}
