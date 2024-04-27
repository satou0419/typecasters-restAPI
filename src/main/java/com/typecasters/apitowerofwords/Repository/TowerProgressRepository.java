package com.typecasters.apitowerofwords.Repository;


import com.typecasters.apitowerofwords.Entity.TowerProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TowerProgressRepository extends JpaRepository<TowerProgressEntity, Integer> {

}
