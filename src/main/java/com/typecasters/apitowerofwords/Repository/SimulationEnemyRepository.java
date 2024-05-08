package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.SimulationEnemyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationEnemyRepository extends JpaRepository<SimulationEnemyEntity, Integer> {
}
