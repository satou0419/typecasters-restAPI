package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationWordsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimulationWordsRepository extends JpaRepository<SimulationWordsEntity, Integer> {
    List<SimulationWordsEntity> findAllByCreatorID(int userID);

    @Query("SELECT sw.word FROM SimulationWordsEntity sw WHERE sw.simulationWordsID = ?1")
    Optional<String> findWordBySimulationWordsID(int simulationWordsID);

    @Query("SELECT sw FROM SimulationWordsEntity sw WHERE sw.creatorID = ?1 AND sw.word = ?2")
    Optional<SimulationWordsEntity> findOneByCreatorIDAndWord(int creatorID, String word);

}
