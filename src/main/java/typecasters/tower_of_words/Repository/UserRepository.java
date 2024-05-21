package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findOneByUsername(String username);
}
