package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import typecasters.tower_of_words.Entity.AdventureEnemyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdventureEnemyRepository extends JpaRepository<AdventureEnemyEntity, Integer> {
    Optional<List<AdventureEnemyEntity>> findAllByTowerFloorID(int towerFloorID);
}
