package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import typecasters.tower_of_words.Entity.ArchiveWordsEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveWordsRepository extends JpaRepository<ArchiveWordsEntity, Integer> {
    List<ArchiveWordsEntity> findAllByUserID(int userID);
    Optional<ArchiveWordsEntity> findByUserIDAndWord(int userID, String word);

    @Query("SELECT a FROM ArchiveWordsEntity a WHERE a.userID = :userID AND EXISTS (SELECT u FROM UserEntity u WHERE u.userID = :userID)")
    List<ArchiveWordsEntity> findAllByUserIDIfExists(@Param("userID") int userID);
}
