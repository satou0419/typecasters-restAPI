package typecasters.tower_of_words.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentWordProgressRepository extends JpaRepository<StudentWordProgressEntity, Integer> {

    List<StudentWordProgressEntity> findAllByStudentID(int studentID);

    @Query("SELECT swp FROM StudentWordProgressEntity swp WHERE swp.studentID = ?1 AND swp.simulationID= ?2")
    List<StudentWordProgressEntity> findAllByStudentIDAndSimulationID(
            int studentID,
            int simulationAttemptsID);

    @Query("SELECT swp FROM StudentWordProgressEntity swp WHERE swp.simulationID= ?1")
    List<StudentWordProgressEntity> findAllBySimulationID(
            int simulationAttemptsID);

    @Query("SELECT swp FROm StudentWordProgressEntity swp WHERE swp.studentID = ?1 AND swp.simulationID = ?2 AND swp.simulationWordsID = ?3")
    Optional<StudentWordProgressEntity> findOneByStudentIDAndSimulationIDAndSimulationWordsID(int studentID, int simulationID, int simulationWordsID);

    @Modifying
    @Transactional
    @Query("DELETE FROM StudentWordProgressEntity swp WHERE swp.simulationID = :simulationID")
    void deleteBySimulationID(@Param("simulationID") int simulationID);

    void deleteByStudentIDAndSimulationID(int studentID, int simulationID);
}
