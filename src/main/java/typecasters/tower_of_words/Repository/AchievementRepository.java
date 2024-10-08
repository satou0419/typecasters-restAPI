package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.AchievementEntity;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Integer> {

    List<AchievementEntity> findByAchievementType(String achievementType);
}
