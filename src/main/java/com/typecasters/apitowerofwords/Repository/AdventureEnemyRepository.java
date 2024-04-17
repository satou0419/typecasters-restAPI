package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdventureEnemyRepository extends JpaRepository<AdventureEnemyEntity, Integer> {

}
