package org.youcode.hunterleague.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.domain.Competition;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.service.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.youcode.hunterleague.service.SpeciesService;
import org.youcode.hunterleague.web.vm.SpeciesVM;
import org.youcode.hunterleague.web.vm.mapper.SpeciesMapper;
import org.youcode.hunterleague.web.vm.response.CompetitionVMResponse;
import org.youcode.hunterleague.web.vm.response.mapper.CompetitionVMResponseMapper;
import org.youcode.hunterleague.web.vm.response.mapper.SpeciesVMResponseMapper;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/competition")
@Validated
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CompetitionVMResponseMapper competitionVMResponseMapper;

    @Autowired
    public CompetitionController(CompetitionService competitionService , CompetitionVMResponseMapper competitionVMResponseMapper) {
        this.competitionService = competitionService;
        this.competitionVMResponseMapper = competitionVMResponseMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCompetition(@Valid @RequestBody Competition competition) {
        competitionService.createCompetition(competition);
        return ResponseEntity.ok("Specy registered successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompetition(@PathVariable UUID id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.ok("Competition deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompetition(
            @PathVariable UUID id,
            @Valid @RequestBody Competition competition) {
        competitionService.updateCompetition(id, competition);
        return ResponseEntity.ok("Competition updated successfully");
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompetitionVMResponse> getCompetitionDetails(@PathVariable UUID id) {
        Competition competition = competitionService.getCompetitionDetails(id);
        CompetitionVMResponse  competitionResponse =  competitionVMResponseMapper.toVMResponse(competition);
        return ResponseEntity.ok(competitionResponse);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CompetitionVMResponse>> getAllCompetitions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Competition> competitions = competitionService.getAllCompetitions(PageRequest.of(page, size));
        Page<CompetitionVMResponse> competitionResponses = competitions.map(competitionVMResponseMapper::toVMResponse);

        return ResponseEntity.ok().body(competitionResponses);
    }


    @GetMapping("/byLocation")
    public ResponseEntity<Page<CompetitionVMResponse>> getCompetitionsByLocation(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Competition> competitions = competitionService.getCompetitionsByLocation(location, page, size);
        Page<CompetitionVMResponse> competitionResponses = competitions.map(competitionVMResponseMapper::toVMResponse);
        return ResponseEntity.ok().body(competitionResponses);
    }


    @GetMapping("/byOpenRegistration")
    public ResponseEntity<Page<CompetitionVMResponse>> getCompetitionsByOpenRegistration(
            @RequestParam boolean openRegistration,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Competition> competitions = competitionService.getCompetitionsByOpenRegistration(openRegistration, page, size);
        Page<CompetitionVMResponse> competitionResponses = competitions.map(competitionVMResponseMapper::toVMResponse);
        return ResponseEntity.ok().body(competitionResponses);
    }






}
