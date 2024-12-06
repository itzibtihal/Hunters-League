package org.youcode.hunterleague.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.hunterleague.domain.entities.Hunt;
import org.youcode.hunterleague.service.DTOs.HuntRequestDTO;
import org.youcode.hunterleague.service.HuntService;

@RestController
@RequestMapping("api/v1/hunt")
@AllArgsConstructor
public class HuntController {

    private final HuntService huntService;


    @PostMapping
    public ResponseEntity<String> createHunt(@RequestBody @Valid HuntRequestDTO huntRequestDTO) {
        Hunt hunt = huntService.createHunt(huntRequestDTO);
        return ResponseEntity.ok("hunt created");
    }
}
