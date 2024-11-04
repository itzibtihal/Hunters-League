package org.youcode.hunterleague.web.vm;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationVm {

    @NotBlank(message = "User is required")
    private UUID userId;

    @NotBlank(message = "Competition  is required")
    private UUID competitionId;

}
