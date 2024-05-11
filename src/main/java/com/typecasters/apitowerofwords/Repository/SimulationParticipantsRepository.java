package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.SimulationEnemyEntity;
import com.typecasters.apitowerofwords.Entity.SimulationParticipantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationParticipantsRepository extends JpaRepository<SimulationParticipantsEntity, Integer> {
}
