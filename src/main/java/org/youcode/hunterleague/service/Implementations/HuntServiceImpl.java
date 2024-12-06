package org.youcode.hunterleague.service.Implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.hunterleague.domain.entities.Hunt;
import org.youcode.hunterleague.domain.entities.Participation;
import org.youcode.hunterleague.domain.entities.Species;
import org.youcode.hunterleague.repository.HuntRepository;
import org.youcode.hunterleague.service.DTOs.HuntRequestDTO;
import org.youcode.hunterleague.service.HuntService;
import org.youcode.hunterleague.service.ParticipationService;
import org.youcode.hunterleague.service.SpeciesService;
import org.youcode.hunterleague.web.exception.InvalidCredentialsException;

@Service
@AllArgsConstructor
public class HuntServiceImpl implements HuntService {

    private final HuntRepository huntRepository;
    private final ParticipationService participationService;
    private final SpeciesService speciesService;

    @Transactional
    @Override
    public Hunt createHunt(HuntRequestDTO huntRequestDTO) {

        Participation participation = participationService.findById(huntRequestDTO.getParticipationId());
        Species species = speciesService.findById(huntRequestDTO.getSpeciesId());

        if (huntRequestDTO.getWeight() < species.getMinimumWeight()) {
            throw new InvalidCredentialsException("Hunt weight must be at least " + species.getMinimumWeight());
        }
        Hunt hunt = Hunt.builder()
                .weight(huntRequestDTO.getWeight())
                .species(species)
                .participation(participation)
                .build();

        Hunt savedHunt = huntRepository.save(hunt);
        participationService.updateParticipationScore(participation);
        return savedHunt;
    }
}

