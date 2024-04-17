package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {
}
