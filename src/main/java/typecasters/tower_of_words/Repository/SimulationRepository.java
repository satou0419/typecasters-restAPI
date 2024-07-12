package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.RoomEntity;
import typecasters.tower_of_words.Entity.SimulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationRepository extends JpaRepository<SimulationEntity, Integer> {
    List<SimulationEntity> findAllByRoomID(RoomEntity roomID);
}
