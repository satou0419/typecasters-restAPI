package typecasters.tower_of_words.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Transactional
    @Query("DELETE  FROM SimulationParticipantsEntity p WHERE p.simulationID = :simulationID")
    void deleteBySimulation(@Param("simulationID") SimulationEntity simulationID);

    // Custom query to delete participants by simulation ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_simulation_users WHERE simulationID = :simulationID", nativeQuery = true)
    void deleteParticipantsBySimulationID(@Param("simulationID") int simulationID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_simulation_users WHERE simulation_participantsid = :simulationParticipantsID", nativeQuery = true)
    void deleteParticipantBySimulationParticipantsID(@Param("simulationParticipantsID") int simulationParticipantsID);

    void deleteByUserIDAndSimulationID(int userID, SimulationEntity simulationID);
}
