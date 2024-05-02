package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    ItemEntity findOneByItemId(Integer itemId);
}
