package org.youcode.hunterleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.youcode.hunterleague.domain.entities.Competition;
import org.youcode.hunterleague.domain.entities.Participation;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.service.DTOs.PodiumDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

    boolean existsByUserAndCompetition(User user, Competition competition);
    List<Participation> findByUserId(UUID userId);
    Optional<Participation> findByUserIdAndCompetitionId(UUID userId, UUID competitionId);

    @Query("SELECT new org.youcode.hunterleague.service.DTOs.PodiumDTO(p.user.username, p.score) " +
            "FROM Participation p " +
            "WHERE p.competition.id = :competitionId " +
            "ORDER BY p.score DESC LIMIT 3")
    List<PodiumDTO> findTopThreeByCompetition(@Param("competitionId") UUID competitionId);

}