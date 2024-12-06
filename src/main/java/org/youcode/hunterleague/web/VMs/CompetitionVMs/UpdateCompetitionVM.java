package org.youcode.hunterleague.web.VMs.CompetitionVMs;

import jakarta.validation.constraints.Min;
import lombok.*;
import org.youcode.hunterleague.domain.enums.SpeciesType;
import org.youcode.hunterleague.validation.EnumValue;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCompetitionVM {

    private String location;

//    @Future(message = "date must be in the future")
    private LocalDateTime date;

    @EnumValue(enumClass = SpeciesType.class , message = "invalid species Type")
    private String speciesType;

    @Min(1)
    private Integer minParticipants;

    @Min(1)
    private Integer maxParticipants;

    private Boolean openRegistration;
}
