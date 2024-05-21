package com.typecasters.apitowerofwords.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdventureEnemyRepository extends JpaRepository<AdventureEnemyEntity, Integer> {
    Optional<List<AdventureEnemyEntity>> findAllByTowerFloorId(int towerFloorId);
}
