package org.youcode.hunterleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.Competition;

import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

}
