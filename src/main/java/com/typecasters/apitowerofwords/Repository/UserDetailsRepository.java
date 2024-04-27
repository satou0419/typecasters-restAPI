package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {

    UserDetailsEntity findOneByUserId(int userId);
}
