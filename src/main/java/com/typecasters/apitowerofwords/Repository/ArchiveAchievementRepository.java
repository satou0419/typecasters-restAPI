package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.typecasters.apitowerofwords.Entity.ArchiveAchievementEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveAchievementRepository extends JpaRepository<ArchiveAchievementEntity, Integer> {
    List<ArchiveAchievementEntity> findByUserID(int userID);
}
