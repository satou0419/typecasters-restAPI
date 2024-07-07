package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.ItemEntity;
import typecasters.tower_of_words.Entity.UserItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserItemRepository extends JpaRepository<UserItemEntity, Integer> {

    List<UserItemEntity> findAllByUserID(int userID);

    @Query("SELECT ui.userItemID FROM UserItemEntity ui WHERE ui.userID = ?1 AND ui.itemID = ?2")
    Optional<Integer> findUserItemIDByUserIDAndItemID(int userID, ItemEntity itemID);

    Optional<UserItemEntity> findOneByUserIDAndItemID(int userID, ItemEntity itemID);

}
