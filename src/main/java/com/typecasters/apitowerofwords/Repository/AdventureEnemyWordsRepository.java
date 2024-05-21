package com.typecasters.apitowerofwords.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.typecasters.apitowerofwords.Entity.AdventureEnemyWordsEntity;

@Repository
public interface AdventureEnemyWordsRepository extends JpaRepository<AdventureEnemyWordsEntity, Integer> {
}
