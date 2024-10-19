package typecasters.tower_of_words.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationWeightedScoreEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimulationWeightedScoreRepository extends JpaRepository<SimulationWeightedScoreEntity, Integer> {

    @Query("SELECT sws FROM SimulationWeightedScoreEntity sws WHERE sws.simulationID = ?1 AND sws.simulationParticipantID = ?2")
    List<SimulationWeightedScoreEntity> findAllBySimulationIDAndSimulationParticipantID(int simulationID, int simulationParticipantID);

    @Query("SELECT sws FROM SimulationWeightedScoreEntity sws WHERE sws.simulationID = ?1 AND sws.simulationParticipantID = ?2 AND sws.wordID = ?3")
    Optional<SimulationWeightedScoreEntity> findOneBySimulationIDAndSimulationParticipantIDAndWordID(int simulationID, int simulationParticipantID, int wordID);

    @Modifying
    @Transactional
    @Query("DELETE FROM SimulationWeightedScoreEntity w WHERE w.simulationID = ?1")
    void deleteBySimulation(int simulationID);

}
