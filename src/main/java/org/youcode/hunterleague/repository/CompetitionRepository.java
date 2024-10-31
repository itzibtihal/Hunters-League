package org.youcode.hunterleague.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.hunterleague.domain.Competition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    Page<Competition> findByLocation(String location, Pageable pageable);

    Page<Competition> findByOpenRegistration(boolean openRegistration, Pageable pageable);

    List<Competition> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
