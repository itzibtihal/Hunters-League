package org.youcode.hunterleague.web.vm.response;

import lombok.Getter;
import lombok.Setter;
import org.youcode.hunterleague.domain.enums.Difficulty;
import org.youcode.hunterleague.domain.enums.SpeciesType;

@Getter
@Setter

public class SpeciesVMResponse {

    private String name;

    private SpeciesType category;

    private Double minimumWeight;

    private Difficulty difficulty;

}
