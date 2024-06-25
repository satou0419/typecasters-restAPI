package typecasters.tower_of_words.Repository;


import typecasters.tower_of_words.Entity.UserProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgressEntity, Integer> {

}
