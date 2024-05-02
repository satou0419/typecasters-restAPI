package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
}
