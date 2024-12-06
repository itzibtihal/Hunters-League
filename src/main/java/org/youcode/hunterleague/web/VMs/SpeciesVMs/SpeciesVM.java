package org.youcode.hunterleague.web.VMs.SpeciesVMs;

import jakarta.validation.constraints.*;
import lombok.*;
import org.youcode.hunterleague.domain.enums.Difficulty;
import org.youcode.hunterleague.domain.enums.SpeciesType;
import org.youcode.hunterleague.validation.EnumValue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpeciesVM {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @EnumValue(enumClass = SpeciesType.class, message = "Invalid category")
    private String category;

    @NotNull
    @Positive
    private Double minimumWeight;

    @NotBlank
    @EnumValue(enumClass = Difficulty.class, message = "Invalid difficulty")
    private String difficulty;

    @NotNull
    @Min(value = 0, message = "Points must be at least 0")
    private Integer points;

}

