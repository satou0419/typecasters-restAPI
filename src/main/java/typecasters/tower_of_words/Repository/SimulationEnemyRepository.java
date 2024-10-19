package typecasters.tower_of_words.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import typecasters.tower_of_words.Entity.SimulationEnemyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.SimulationEntity;

@Repository
public interface SimulationEnemyRepository extends JpaRepository<SimulationEnemyEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_simulation_enemy WHERE simulationID = :simulationID", nativeQuery = true)
    void deleteEnemiesBySimulation(@Param("simulationID") int simulationID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_simulation_enemy WHERE simulation_enemyid = :simulationEnemyID", nativeQuery = true)
    void deleteWordsBySimulationEnemyID(@Param("simulationEnemyID") int simulationEnemyID);

}
