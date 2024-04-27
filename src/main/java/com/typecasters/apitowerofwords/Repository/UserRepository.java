package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findOneByUsername(String username);
}
