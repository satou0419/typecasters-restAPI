package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.AdventureRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdventureRewardRepository extends JpaRepository<AdventureRewardEntity, Integer> {

    Optional<AdventureRewardEntity> findByTowerFloorId(int towerFloorId);
}
