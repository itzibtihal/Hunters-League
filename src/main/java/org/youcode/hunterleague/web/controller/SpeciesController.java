package org.youcode.hunterleague.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.domain.enums.SpeciesType;
import org.youcode.hunterleague.service.SpeciesService;
import org.youcode.hunterleague.web.vm.response.SpeciesVMResponse;
import org.youcode.hunterleague.web.vm.response.mapper.SpeciesVMResponseMapper;
import org.youcode.hunterleague.web.vm.SpeciesVM;
import org.youcode.hunterleague.web.vm.mapper.SpeciesMapper;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/api/species")
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesMapper speciesMapper;
    private final SpeciesVMResponseMapper speciesVMResponseMapper;

    @Autowired
    public SpeciesController(SpeciesService speciesService,SpeciesMapper speciesMapper , SpeciesVMResponseMapper speciesVMResponseMapper) {
        this.speciesService = speciesService;
        this.speciesMapper = speciesMapper;
        this.speciesVMResponseMapper = speciesVMResponseMapper;
    }

    @GetMapping("/getByCategory")
    public Page<SpeciesVMResponse> getSpeciesByCategory(
            @RequestParam SpeciesType category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Species> speciesPage = speciesService.getSpeciesByCategory(category, page, size);

        return new PageImpl<>(
                speciesPage.stream()
                        .map(speciesVMResponseMapper::toDto)
                        .collect(Collectors.toList()),
                PageRequest.of(page, size),
                speciesPage.getTotalElements()
        );
    }


//    @PostMapping("/save")
//    public ResponseEntity<String> saveSpecies(@Valid @RequestBody SpeciesVM speciesVM) {
//        Species species = speciesMapper.toEntity(speciesVM);
//        speciesService.saveSpecies(species);
//        return ResponseEntity.ok("Specy registered successfully");
//    }

    @PostMapping("/save")
    public ResponseEntity<String> saveSpecies(@Valid @RequestBody Species species) {
        //Species species = speciesMapper.toEntity(species);
        speciesService.saveSpecies(species);
        return ResponseEntity.ok("Specy registered successfully");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpecies(@PathVariable UUID id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.ok("Specy deleted successfully");
    }




}

