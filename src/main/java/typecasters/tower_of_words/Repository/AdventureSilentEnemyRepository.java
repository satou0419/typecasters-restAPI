package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.AdventureSilentEnemyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdventureSilentEnemyRepository extends JpaRepository<AdventureSilentEnemyEntity, Integer> {
    Optional<List<AdventureSilentEnemyEntity>> findAllByTowerFloorID(int towerFloorID);
}
