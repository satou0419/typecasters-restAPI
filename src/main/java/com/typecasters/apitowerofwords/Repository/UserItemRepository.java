package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.ItemEntity;
import com.typecasters.apitowerofwords.Entity.UserItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserItemRepository extends JpaRepository<UserItemEntity, Integer> {

    List<UserItemEntity> findAllByUserId(int userId);

    @Query("SELECT ui.userItemId FROM UserItemEntity ui WHERE ui.userId = ?1 AND ui.itemId = ?2")
    Optional<Integer> findUserItemIdByUserIdAndItemId(int userId, ItemEntity itemId);
}
