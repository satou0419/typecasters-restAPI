package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {
    UserDetailsEntity findOneByUserID(int userID);

    @Query("SELECT ud.creditAmount FROM UserDetailsEntity ud WHERE ud.userDetailsID = ?1")
    Optional<Integer> findCreditAmountByUserDetailsID(int userDetailsID);
}
