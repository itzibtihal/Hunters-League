package org.youcode.hunterleague.service;

import org.youcode.hunterleague.domain.entities.Participation;
import org.youcode.hunterleague.service.DTOs.ParticipationResultDTO;
import org.youcode.hunterleague.service.DTOs.PodiumDTO;

import java.util.List;
import java.util.UUID;

public interface ParticipationService {
    Participation registerUserToCompetition(UUID userId, UUID competitionId);
    Participation findById(UUID id);
    void updateParticipationScore(Participation participation);
    List<ParticipationResultDTO> getUserResults(UUID userId);
    ParticipationResultDTO getUserCompetitionResult(UUID userId, UUID competitionId);
    List<PodiumDTO> getTopThreeParticipants(UUID competitionId);
}
