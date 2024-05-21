package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import typecasters.tower_of_words.Entity.AdventureEnemyWordsEntity;

@Repository
public interface AdventureEnemyWordsRepository extends JpaRepository<AdventureEnemyWordsEntity, Integer> {
}
