package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.Query;
import typecasters.tower_of_words.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findOneByUsername(String username);

    @Query("SELECT u.userID FROM UserEntity u WHERE u.username = ?1")
    Optional<Integer> findUserIdByUsername(String username);
}
