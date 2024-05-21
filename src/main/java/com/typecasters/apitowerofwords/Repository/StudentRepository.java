package com.typecasters.apitowerofwords.Repository;

import com.typecasters.apitowerofwords.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    // You can add custom query methods here if needed
}
