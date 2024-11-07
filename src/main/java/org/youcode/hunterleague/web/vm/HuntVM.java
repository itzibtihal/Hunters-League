package org.youcode.hunterleague.web.vm;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuntVM {

    @NotNull(message = "Species ID must not be null")
    private UUID speciesId;

    @NotNull(message = "Participation ID must not be null")
    private UUID participationId;

    @NotNull(message = "Weight must not be null")
    private Double weight;
}