package org.youcode.hunterleague.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.service.CompetitionService;
import org.youcode.hunterleague.service.DTOs.CompetitionDTO;
import org.youcode.hunterleague.web.VMs.CompetitionVMs.CompetitionVM;
import org.youcode.hunterleague.web.VMs.CompetitionVMs.CreateCompetitionVM;
import org.youcode.hunterleague.web.VMs.CompetitionVMs.UpdateCompetitionVM;
import org.youcode.hunterleague.web.VMs.mapper.CompetitionVMMapper;
import org.youcode.hunterleague.web.VMs.mapper.CreateCompetitionVMMapper;
import org.youcode.hunterleague.web.VMs.mapper.UpdateCompetitionVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/competition")
@Validated
@AllArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;
    private final CreateCompetitionVMMapper createCompetitionVMMapper;
    private final CompetitionVMMapper competitionVMMapper;
    private final UpdateCompetitionVMMapper updateCompetitionVMMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_VIEW_COMPETITIONS')")
    public ResponseEntity<Page<CompetitionVM>> getAllCompetitions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Competition> competitions = competitionService.findAllCompetitionsPaginated(page,size);
        List<CompetitionVM> competitionVMSList = competitions.stream().map(competitionVMMapper::toCompetitionVM).toList();
        Page<CompetitionVM> competitionVMS = new PageImpl<>(competitionVMSList,competitions.getPageable(),competitions.getTotalElements());
        return ResponseEntity.ok(competitionVMS);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('CAN_MANAGE_COMPETITIONS')")
    public ResponseEntity<Competition> createCompetition(@Valid @RequestBody CreateCompetitionVM createCompetitionVM) {
        Competition competition = createCompetitionVMMapper.toCompetition(createCompetitionVM);
        Competition savedCompetition = competitionService.createCompetition(competition);
        return new ResponseEntity<>(savedCompetition, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_VIEW_COMPETITIONS')")
    public ResponseEntity<CompetitionDTO> getCompetitionDetails(@PathVariable UUID id) {
        CompetitionDTO competitionDTO = competitionService.getCompetitionDetails(id);
        return ResponseEntity.ok(competitionDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('CAN_MANAGE_COMPETITIONS')")
    public ResponseEntity<String> deleteCompetition(@PathVariable UUID id){
        boolean isDeleted = competitionService.delete(id);
        if (isDeleted){
            return ResponseEntity.ok("deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete competition");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CAN_MANAGE_COMPETITIONS')")
    public ResponseEntity<CompetitionVM> update(@PathVariable UUID id, @RequestBody @Valid UpdateCompetitionVM updateData){
        Competition competition = updateCompetitionVMMapper.toCompetition(updateData);
        Competition updatedCompetition = competitionService.update(id,competition);
        CompetitionVM competitionVM = competitionVMMapper.toCompetitionVM(updatedCompetition);
        return ResponseEntity.ok(competitionVM);
    }
}
