package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.SimulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationRepository extends JpaRepository<SimulationEntity, Integer> {
    List<SimulationEntity> findAllByRoomID(int roomID);
}
