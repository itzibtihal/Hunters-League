package org.youcode.hunterleague.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.domain.entities.Participation;
import org.youcode.hunterleague.service.DTOs.ParticipationResultDTO;
import org.youcode.hunterleague.service.DTOs.PodiumDTO;
import org.youcode.hunterleague.service.ParticipationService;
import org.youcode.hunterleague.web.VMs.ParticipationVMs.ParticipationRequestVM;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/participation")
@AllArgsConstructor
@Validated
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping
    public ResponseEntity<Participation> registerUserToCompetition(@RequestBody @Valid ParticipationRequestVM participationRequestVM){
        Participation participation = participationService.registerUserToCompetition(participationRequestVM.getUserId(),participationRequestVM.getCompetitionId());
        return ResponseEntity.ok(participation);
    }

    @GetMapping("/results")
    public ResponseEntity<List<ParticipationResultDTO>> getUserAllCompetitionsResults(@RequestParam UUID userId) {
        List<ParticipationResultDTO> results = participationService.getUserResults(userId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/competition-result")
    public ResponseEntity<ParticipationResultDTO> getUserSingleCompetitionResult(
            @RequestParam UUID userId, @RequestParam UUID competitionId) {

        ParticipationResultDTO result = participationService.getUserCompetitionResult(userId, competitionId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/podium")
    public ResponseEntity<List<PodiumDTO>> getTopThreeParticipants(@RequestParam UUID competitionId){
        List<PodiumDTO> podium = participationService.getTopThreeParticipants(competitionId);
        return ResponseEntity.ok(podium);
    }
}
