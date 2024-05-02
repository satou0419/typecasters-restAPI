package com.typecasters.apitowerofwords.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;

@Repository
public interface AdventureEnemyRepository extends JpaRepository<AdventureEnemyEntity, Integer> {
}
