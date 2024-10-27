package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.UserGameTutorialStatusEntity;

import java.util.Optional;

@Repository
public interface UserGameTutorialStatusRepository extends JpaRepository<UserGameTutorialStatusEntity, Integer>{

    @Query("SELECT ugt FROM UserGameTutorialStatusEntity ugt WHERE ugt.userDetailsID = ?1")
    Optional<UserGameTutorialStatusEntity> findOneByUserDetailsID(int userDetailsID);

}
