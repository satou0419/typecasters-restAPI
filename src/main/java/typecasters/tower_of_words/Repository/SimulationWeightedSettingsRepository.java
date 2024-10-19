package typecasters.tower_of_words.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationWeightedSettingsEntity;

import java.util.Optional;

@Repository
public interface SimulationWeightedSettingsRepository extends JpaRepository<SimulationWeightedSettingsEntity, Integer> {

    @Query("SELECT sws FROM SimulationWeightedSettingsEntity sws WHERE sws.creatorID = ?1 AND sws.simulationID = ?2")
    Optional<SimulationWeightedSettingsEntity> findOneByCreatorIDAndSimulationID(int creatorID, int simulationID);

    @Modifying
    @Transactional
    @Query("DELETE FROM SimulationWeightedSettingsEntity s WHERE s.simulationID = ?1")
    void deleteBySimulation(int simulationID);
}
