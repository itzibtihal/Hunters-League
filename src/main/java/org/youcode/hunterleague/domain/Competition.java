package org.youcode.hunterleague.domain;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.hunterleague.domain.enums.SpeciesType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue
    private UUID id;

    private String code;

    private String location;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private SpeciesType speciesType;

    private Integer minParticipants;

    private Integer maxParticipants;

    private Boolean openRegistration;

    @OneToMany(mappedBy = "competition")
    private List<Participation> participations;

}
