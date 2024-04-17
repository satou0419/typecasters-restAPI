package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<UserItemEntity, Integer> {

}
