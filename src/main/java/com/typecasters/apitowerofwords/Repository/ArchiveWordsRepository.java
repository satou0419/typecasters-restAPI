package com.typecasters.apitowerofwords.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.typecasters.apitowerofwords.Entity.ArchiveWordsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveWordsRepository extends JpaRepository<ArchiveWordsEntity, Integer> {
}
