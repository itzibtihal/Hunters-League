package org.youcode.hunterleague.domain;


import jakarta.persistence.*;
import lombok.*;
import org.youcode.hunterleague.domain.enums.Difficulty;
import org.youcode.hunterleague.domain.enums.SpeciesType;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private SpeciesType category;

    private Double minimumWeight;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Integer points;

}
