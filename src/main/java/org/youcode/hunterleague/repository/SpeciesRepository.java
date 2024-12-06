package org.youcode.hunterleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.hunterleague.domain.entities.Species;

import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {
}
