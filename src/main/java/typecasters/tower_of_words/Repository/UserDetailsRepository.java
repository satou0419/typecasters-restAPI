package typecasters.tower_of_words.Repository;

import typecasters.tower_of_words.Entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {

    UserDetailsEntity findOneByUserId(int userId);

    @Query("SELECT ud.credit_amount FROM UserDetailsEntity ud WHERE ud.user_detail_id = ?1")
    Optional<Integer> findCreditAmountByUserDetailId(int userDetailId);

    @Query("SELECT ud.user_detail_id FROM UserDetailsEntity ud WHERE ud.userId = ?1")
    Optional<Integer> findUserDetailIdByUserId(int userId);
}
