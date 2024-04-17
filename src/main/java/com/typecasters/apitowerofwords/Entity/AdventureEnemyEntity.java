package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name="tbladventure_enemy")
public class AdventureEnemyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enemyId;

    @Column(name = "enemy_name")
    private String enemyName;

    @Column(name = "word")
    private String word;

    @Column(name = "image_path")
    private String imagePath;

    public AdventureEnemyEntity() {
    }

    public AdventureEnemyEntity(int enemyId, String enemyName, String word, String imagePath) {
        this.enemyId = enemyId;
        this.enemyName = enemyName;
        this.word = word;
        this.imagePath = imagePath;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
