package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_adventure_enemy")
public class AdventureEnemyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AEID;

    private String imagePath;
    private int TFID;
    private boolean isDeleted = false;

    public AdventureEnemyEntity() {
    }

    public AdventureEnemyEntity(int AEID, String imagePath, int TFID, boolean isDeleted) {
        this.AEID = AEID;
        this.imagePath = imagePath;
        this.TFID = TFID;
        this.isDeleted = isDeleted;
    }

    public int getAEID() {
        return AEID;
    }

    public void setAEID(int AEID) {
        this.AEID = AEID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getTFID() {
        return TFID;
    }

    public void setTFID(int TFID) {
        this.TFID = TFID;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
