package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.SimulationAttemptsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimulationAttemptsRepository extends JpaRepository<SimulationAttemptsEntity, Integer> {

//    @Query("SELECT sa FROM SimulationAttemptsEntity sa WHERE sa.simulationID = ?1 AND sa.simulationParticipantsID = ?2 AND sa.currentAttempt = ?3")
//    Optional<SimulationAttemptsEntity> findOneBySimulationIDAndSimulationParticipantsID(
//            int simulationID,
//            int simulationParticipantsID,
//            int currentAttempt);
//
//    @Query("SELECT sa FROM SimulationAttemptsEntity sa WHERE sa.simulationID = ?1 AND sa.simulationParticipantsID = ?2")
//    List<SimulationAttemptsEntity> findBySimulationIDAndSimulationParticipantsID(
//            int simulationID,
//            int simulationParticipantsID);
}
