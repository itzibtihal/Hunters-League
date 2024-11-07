package org.youcode.hunterleague.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youcode.hunterleague.domain.Hunt;

import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {
}
