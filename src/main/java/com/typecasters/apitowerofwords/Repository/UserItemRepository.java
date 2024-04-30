package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserItemRepository extends JpaRepository<UserItemEntity, Integer> {

}
