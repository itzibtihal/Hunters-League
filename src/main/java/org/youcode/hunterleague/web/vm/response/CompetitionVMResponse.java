package org.youcode.hunterleague.web.vm.response;

import lombok.*;
import org.youcode.hunterleague.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionVMResponse {
    private UUID id;
    private String code;
    private String location;
    private LocalDateTime date;
    private SpeciesType speciesType;
    private Integer minParticipants;
    private Integer maxParticipants;
    private Boolean openRegistration;
    private Integer participantCount;
}
