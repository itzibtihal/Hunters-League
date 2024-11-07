package org.youcode.hunterleague.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.domain.Hunt;
import org.youcode.hunterleague.service.HuntServiceImpl;
import org.youcode.hunterleague.web.vm.HuntVM;
import org.youcode.hunterleague.web.vm.mapper.HuntMapper;

@RestController
@RequestMapping("/v1/api/hunts")
public class HuntController {

    private final HuntServiceImpl huntService;
    private final HuntMapper huntMapper = HuntMapper.INSTANCE;

    @Autowired
    public HuntController(HuntServiceImpl huntService) {
        this.huntService = huntService;
    }

    @PostMapping("/save")
    public ResponseEntity<HuntVM> saveHunt(@Valid @RequestBody HuntVM huntViewModel) {
        Hunt hunt = huntMapper.toEntity(huntViewModel);
        Hunt savedHunt = huntService.saveHunt(hunt);
        HuntVM savedHuntViewModel = huntMapper.toVM(savedHunt);
        return ResponseEntity.ok(savedHuntViewModel);
    }
}