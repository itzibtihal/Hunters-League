package org.youcode.hunterleague.service.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HuntRequestDTO {

    @NotNull(message = "Species ID cannot be null")
    private UUID participationId;

    @NotNull(message = "Participation ID cannot be null")

    private UUID speciesId;

    @Positive(message = "Weight must be a positive number")
    private Double weight;
}
