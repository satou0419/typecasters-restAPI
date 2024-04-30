package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.TowerFloorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TowerFloorRepository extends JpaRepository<TowerFloorEntity, Integer> {
}
