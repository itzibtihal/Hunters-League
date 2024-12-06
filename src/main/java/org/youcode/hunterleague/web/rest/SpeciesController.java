package org.youcode.hunterleague.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.domain.entities.Species;
import org.youcode.hunterleague.service.SpeciesService;
import org.youcode.hunterleague.web.VMs.SpeciesVMs.SpeciesVM;
import org.youcode.hunterleague.web.VMs.mapper.SpeciesVMMapper;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/species")
@AllArgsConstructor
@Validated
@PreAuthorize("hasRole('ADMIN')")
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesVMMapper speciesVMMapper;

    @PostMapping
    public ResponseEntity<Species> createSpecies(@RequestBody @Valid SpeciesVM speciesVM) {
        Species species = speciesVMMapper.toSpecies(speciesVM);
        Species createdSpecies = speciesService.createSpecies(species);
        return ResponseEntity.ok(createdSpecies);
    }

    @GetMapping
    public ResponseEntity<Page<Species>> getAllSpecies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Species> species = speciesService.getAllSpecies(page,size);
        return ResponseEntity.ok(species);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable UUID id){
        speciesService.delete(id);
        return ResponseEntity.ok("species is deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> updateSpecies(
            @PathVariable UUID id,
            @RequestBody Species speciesUpdates) {

        Species updatedSpecies = speciesService.updateSpecies(id, speciesUpdates);
        return ResponseEntity.ok(updatedSpecies);
    }
}
