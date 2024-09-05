package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.AchievementEntity;
import typecasters.tower_of_words.Entity.ArchiveAchievementEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveAchievementRepository extends JpaRepository<ArchiveAchievementEntity, Integer> {
    List<ArchiveAchievementEntity> findByUserID(int userID);

    Optional<ArchiveAchievementEntity> findOneByUserIDAndAchievementID(int userID, AchievementEntity achievementID);
}
