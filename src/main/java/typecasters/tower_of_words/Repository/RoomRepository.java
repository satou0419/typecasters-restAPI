package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import typecasters.tower_of_words.Entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    List<RoomEntity> findAllByCreatorID(int creatorID);
    RoomEntity findByCode(String code);

    @Query("SELECT r FROM RoomEntity r JOIN r.simulations s WHERE s.simulationID = :simulationID AND :userID MEMBER OF r.members")
    Optional<RoomEntity> findOneBySimulationIDAndUserID(@Param("simulationID") int simulationID, @Param("userID") int userID);
}
