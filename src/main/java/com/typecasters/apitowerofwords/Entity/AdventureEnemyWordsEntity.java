package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_adventure_enemy_words")
public class AdventureEnemyWordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AEWID;

    private String word;
    private boolean isDeleted = false;

    public AdventureEnemyWordsEntity() {
    }

    public AdventureEnemyWordsEntity(int AEWID, String word, boolean isDeleted) {
        this.AEWID = AEWID;
        this.word = word;
        this.isDeleted = isDeleted;
    }

    public int getAEWID() {
        return AEWID;
    }

    public void setAEWID(int AEWID) {
        this.AEWID = AEWID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
