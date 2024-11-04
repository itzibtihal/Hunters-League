package org.youcode.hunterleague.service;

import java.util.UUID;

public interface ParticipationService {
    void participateInCompetition(UUID userId, UUID competitionId);
}
