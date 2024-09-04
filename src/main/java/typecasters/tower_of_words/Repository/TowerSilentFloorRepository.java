package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.TowerSilentFloorEntity;

@Repository
public interface TowerSilentFloorRepository extends JpaRepository<TowerSilentFloorEntity, Integer> {
}
