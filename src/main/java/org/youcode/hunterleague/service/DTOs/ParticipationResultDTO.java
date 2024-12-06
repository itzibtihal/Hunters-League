package org.youcode.hunterleague.service.DTOs;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.youcode.hunterleague.domain.enums.SpeciesType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipationResultDTO {
    private String competitionCode;
    @Enumerated(EnumType.STRING)
    private SpeciesType speciesType;
    private Double score;
}
