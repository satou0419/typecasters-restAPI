package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import typecasters.tower_of_words.Entity.ArchiveWordsEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveWordsRepository extends JpaRepository<ArchiveWordsEntity, Integer> {
    List<ArchiveWordsEntity> findAllByUserID(int userID);
    Optional<ArchiveWordsEntity> findByUserIDAndWord(int userID, String word);
}
