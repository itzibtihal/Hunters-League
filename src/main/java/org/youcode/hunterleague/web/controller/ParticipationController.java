package org.youcode.hunterleague.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.service.ParticipationServiceImpl;
import org.youcode.hunterleague.web.vm.ParticipationVm;


@RestController
@RequestMapping("v1/api/participations")
public class ParticipationController {

    private final ParticipationServiceImpl participationService;

    @Autowired
    public ParticipationController(ParticipationServiceImpl participationService) {
        this.participationService = participationService;
    }

    @PostMapping("/participateInCompetition")
    public ResponseEntity<String> participateInCompetition(@RequestBody ParticipationVm participationVm) {
        try {
            participationService.participateInCompetition(participationVm.getUserId(), participationVm.getCompetitionId());
            return ResponseEntity.ok("Participation successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}