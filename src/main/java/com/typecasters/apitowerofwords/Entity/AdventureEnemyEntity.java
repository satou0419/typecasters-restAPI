package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_adventure_enemy")
public class AdventureEnemyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enemy_id")
    private int enemyId;

    private int towerFloorId;
    private String imagePath;

    //    @OneToMany(mappedBy = "enemy", cascade = CascadeType.ALL, orphanRemoval = true)
    @ElementCollection
    @CollectionTable(name = "adventure_words", joinColumns = @JoinColumn(name = "enemyId"))
    private List<String> words = new ArrayList<>();

    public AdventureEnemyEntity() {
    }

    public AdventureEnemyEntity(int enemyId, int towerFloorId, String imagePath, List<String> words) {
        this.enemyId = enemyId;
        this.towerFloorId = towerFloorId;
        this.imagePath = imagePath;
        this.words = words;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public int getTowerFloorId() {
        return towerFloorId;
    }

    public void setTowerFloorId(int towerFloorId) {
        this.towerFloorId = towerFloorId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}