package com.typecasters.apitowerofwords.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.typecasters.apitowerofwords.Entity.ArchiveWordsEntity;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArchiveWordsRepository extends JpaRepository<ArchiveWordsEntity, Integer> {
    List<ArchiveWordsEntity> findByUserID(int userID);
}
