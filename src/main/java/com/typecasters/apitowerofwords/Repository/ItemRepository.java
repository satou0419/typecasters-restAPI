package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    ItemEntity findOneByItemId(Integer itemId);

    @Query("SELECT i.item_name FROM ItemEntity i WHERE i.itemId = ?1")
    String findItemNameByItemId(Integer itemId);

}
