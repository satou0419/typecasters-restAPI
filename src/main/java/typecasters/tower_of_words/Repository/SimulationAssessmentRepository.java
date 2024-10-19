package typecasters.tower_of_words.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.SimulationAssessmentEntity;

import java.util.Optional;

@Repository
public interface SimulationAssessmentRepository extends JpaRepository<SimulationAssessmentEntity, Integer> {

    @Query("SELECT sa FROM SimulationAssessmentEntity sa WHERE simulationID = ?1")
    Optional<SimulationAssessmentEntity> findOneBySimulationID(int simulationID);

    @Query("SELECT sa FROM SimulationAssessmentEntity sa WHERE simulationID = ?1 AND simulationAssessmentID = ?2")
    Optional<SimulationAssessmentEntity> findOneBySimulationIDANDSimulationAssessmentID(int simulationID, int simulationAssessmentID);

    @Modifying
    @Transactional
    @Query("DELETE FROM SimulationAssessmentEntity s WHERE s.simulationID = :simulationID")
    void deleteBySimulationID(@Param("simulationID") int simulationID);
}
