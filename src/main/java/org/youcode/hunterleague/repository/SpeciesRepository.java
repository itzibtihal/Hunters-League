package org.youcode.hunterleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.Species;
import org.youcode.hunterleague.domain.enums.SpeciesType;

import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {

    Page<Species> findByCategory(SpeciesType category, Pageable pageable);

}
