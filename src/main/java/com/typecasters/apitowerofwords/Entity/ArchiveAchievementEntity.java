package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_archive_achievement")
public class ArchiveAchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AAID;

    private String name;
    private String description;
    private String imagePath;
    private boolean isDeleted = false;

    public ArchiveAchievementEntity() {
    }

    public ArchiveAchievementEntity(int AAID, String name, String description, String imagePath, boolean isDeleted) {
        this.AAID = AAID;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.isDeleted = isDeleted;
    }

    public int getAAID() {
        return AAID;
    }

    public void setAAID(int AAID) {
        this.AAID = AAID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
