package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.CharacterEntity;
import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Entity.UserCharacterEntity;
import typecasters.tower_of_words.Entity.UserItemEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacterEntity, Integer> {

    Optional<UserCharacterEntity> findOneByUserIDAndCharacterID(int userID, CharacterEntity characterID);

    @Query("SELECT uc.userCharacterID FROM UserCharacterEntity uc WHERE uc.userID =?1 AND uc.characterID =?2")
    Optional<Integer> findUserCharacterIDByUserIDAndCharacterID(int userID, CharacterEntity characterID);

    List<UserCharacterEntity> findAllByUserID(int userID);
}
