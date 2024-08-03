package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.SimulationParticipantsEntity;
import typecasters.tower_of_words.Entity.SimulationWordAssessmentEntity;

import java.util.List;

@Repository
public interface SimulationWordAssessmentRepository extends JpaRepository<SimulationWordAssessmentEntity, Integer> {

    @Query("SELECT swa FROM SimulationWordAssessmentEntity swa WHERE simulationID = ?1")
    List<SimulationWordAssessmentEntity> findAllBySimulationID(int simulationID);

}
