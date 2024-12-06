package org.youcode.hunterleague.service;

import org.youcode.hunterleague.domain.entities.Hunt;
import org.youcode.hunterleague.service.DTOs.HuntRequestDTO;


public interface HuntService {
    Hunt createHunt(HuntRequestDTO huntRequestDTO);
}
