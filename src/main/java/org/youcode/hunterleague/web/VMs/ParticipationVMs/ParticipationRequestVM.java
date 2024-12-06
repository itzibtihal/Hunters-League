package org.youcode.hunterleague.web.VMs.ParticipationVMs;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipationRequestVM {

    @NotNull(message = "user id is required")
    private UUID userId;

    @NotNull(message = "competition id is required")
    private UUID competitionId;
}
