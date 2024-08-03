package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.SimulationWeightedSettingsEntity;

import java.util.Optional;

@Repository
public interface SimulationWeightedSettingsRepository extends JpaRepository<SimulationWeightedSettingsEntity, Integer> {

    @Query("SELECT sws FROM SimulationWeightedSettingsEntity sws WHERE sws.creatorID = ?1 AND sws.simulationID = ?2")
    Optional<SimulationWeightedSettingsEntity> findOneByCreatorIDAndSimulationID(int creatorID, int simulationID);
}
