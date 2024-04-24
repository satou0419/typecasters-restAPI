package com.typecasters.apitowerofwords.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.typecasters.apitowerofwords.Entity.ArchiveAchievementEntity;

@Repository
public interface ArchiveAchievementRepository extends JpaRepository<ArchiveAchievementEntity, Integer> {
}
