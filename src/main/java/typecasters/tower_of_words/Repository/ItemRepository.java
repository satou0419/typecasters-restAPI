package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    ItemEntity findOneByItemID(Integer itemID);

    @Query("SELECT i.name FROM ItemEntity i WHERE i.itemID = ?1")
    String findNameByItemID(Integer itemID);

}
