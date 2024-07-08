package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import typecasters.tower_of_words.Entity.GameTypeEntity;

@Repository
public interface GameTypeRepository extends JpaRepository<GameTypeEntity, Integer> {

    GameTypeEntity findOneByGameTypeID(int gameTypeID);
}
