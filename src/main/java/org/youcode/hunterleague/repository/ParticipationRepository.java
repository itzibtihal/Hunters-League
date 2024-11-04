package org.youcode.hunterleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.Competition;
import org.youcode.hunterleague.domain.Participation;
import org.youcode.hunterleague.domain.User;

import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    boolean existsByUserAndCompetition(User user, Competition competition);
}
