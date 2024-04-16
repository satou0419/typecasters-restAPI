package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
}
