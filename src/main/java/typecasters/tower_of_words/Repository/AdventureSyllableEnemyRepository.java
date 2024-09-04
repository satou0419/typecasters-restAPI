package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.AdventureSyllableEnemyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdventureSyllableEnemyRepository extends JpaRepository<AdventureSyllableEnemyEntity, Integer> {
    Optional<List<AdventureSyllableEnemyEntity>> findAllByTowerFloorID(int towerFloorID);
}
