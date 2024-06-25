package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.AdventureRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdventureRewardRepository extends JpaRepository<AdventureRewardEntity, Integer> {
    Optional<AdventureRewardEntity> findByTowerFloorID(int towerFloorID);
}
