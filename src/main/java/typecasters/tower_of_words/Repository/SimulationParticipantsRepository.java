package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.Query;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimulationParticipantsRepository extends JpaRepository<SimulationParticipantsEntity, Integer> {

    @Query("SELECT sp.simulationParticipantsID FROM SimulationParticipantsEntity sp WHERE sp.userID = ?1 AND sp.simulationID = ?2")
    Optional<Integer> findSimulationParticipantsIDByUserIDAndSimulationID(Integer userID, SimulationEntity simulationID);


    @Query("SELECT sp FROM SimulationParticipantsEntity sp WHERE sp.simulationParticipantsID = ?1 AND sp.simulationID = ?2")
    Optional<SimulationParticipantsEntity> findOneBySimulationParticipantsIDAndSimulationID(int simulationParticipantsID, SimulationEntity simulationID);


    @Query("SELECT sp FROM SimulationParticipantsEntity sp WHERE sp.simulationID = ?1")
    List<SimulationParticipantsEntity> findAllBySimulationID(SimulationEntity simulationID);

    @Query("SELECT sp FROM SimulationParticipantsEntity sp WHERE sp.userID = ?1 AND sp.simulationID = ?2")
    Optional<SimulationParticipantsEntity> findOneByUserIDAndSimulationID(int userID, SimulationEntity simulationID);

}
