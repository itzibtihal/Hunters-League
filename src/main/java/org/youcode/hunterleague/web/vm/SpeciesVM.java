package org.youcode.hunterleague.web.vm;

import jakarta.validation.constraints.*;
import lombok.*;
import org.youcode.hunterleague.domain.enums.Difficulty;
import org.youcode.hunterleague.domain.enums.SpeciesType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesVM {


    @NotBlank(message = "Species name is required.")
    @Size(max = 50, message = "Species name cannot exceed 50 characters.")
    private String name;

    @NotNull(message = "Species category is required.")
    private SpeciesType category;

    @NotNull(message = "Minimum weight is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum weight must be greater than 0.")
    private Double minimumWeight;

    @NotNull(message = "Difficulty level is required.")
    private Difficulty difficulty;

    @NotNull(message = "Points are required.")
    @Min(value = 0, message = "Points must be 0 or greater.")
    private Integer points;

}
