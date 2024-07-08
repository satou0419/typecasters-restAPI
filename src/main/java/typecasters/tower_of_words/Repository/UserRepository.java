package typecasters.tower_of_words.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import typecasters.tower_of_words.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findOneByUsername(String username);

    Optional<UserEntity> findOneByUserID(int userID);

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE UserEntity u SET u.firstname = :firstname, u.lastname = :lastname WHERE u.userID = :userID")
    void updateUserInfo(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("userID") int userID);

    @Query("SELECT u.userID FROM UserEntity u WHERE u.username = ?1")
    Optional<Integer> findUserIdByUsername(String username);
}
