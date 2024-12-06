package org.youcode.hunterleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.service.DTOs.CompetitionDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    Optional<Competition> findByCode(String code);

    @Query(value = "SELECT * FROM competition c WHERE EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM :date) " +
            "AND EXTRACT(WEEK FROM c.date) = EXTRACT(WEEK FROM :date)",
            nativeQuery = true)
    List<Competition> findCompetitionsInSameWeek(@Param("date") LocalDateTime date);

    @Query("SELECT new org.youcode.hunterleague.service.DTOs.CompetitionDTO(c.code, c.location, c.date, COUNT(p)) " +
            "FROM Competition c LEFT JOIN c.participations p " +
            "WHERE c.id = :competitionId " +
            "GROUP BY c.code, c.location, c.date")
    CompetitionDTO findCompetitionDetailsById(@Param("competitionId") UUID competitionId);

    List<Competition> findByDateBetweenAndOpenRegistrationIsTrue(LocalDateTime now, LocalDateTime next24Hours);

}