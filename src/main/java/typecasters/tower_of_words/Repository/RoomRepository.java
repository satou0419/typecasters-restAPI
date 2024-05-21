package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    List<RoomEntity> findAllByCreatorID(int creatorID);
    RoomEntity findByCode(String code);
}
