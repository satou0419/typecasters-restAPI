package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_archive_achievement")
public class ArchiveAchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int archive_achievement_id;

    private String archive_achievement_name;
    private String archive_achievement_description;
    private String archive_achievement_image_path;
    private boolean archive_achievement_is_deleted = false;

    public ArchiveAchievementEntity() {
    }

    public ArchiveAchievementEntity(int archive_achievement_id, String archive_achievement_name, String archive_achievement_description, String archive_achievement_image_path, boolean archive_achievement_is_deleted) {
        this.archive_achievement_id = archive_achievement_id;
        this.archive_achievement_name = archive_achievement_name;
        this.archive_achievement_description = archive_achievement_description;
        this.archive_achievement_image_path = archive_achievement_image_path;
        this.archive_achievement_is_deleted = archive_achievement_is_deleted;
    }

    public int getArchive_achievement_id() {
        return archive_achievement_id;
    }

    public void setArchive_achievement_id(int archive_achievement_id) {
        this.archive_achievement_id = archive_achievement_id;
    }

    public String getArchive_achievement_name() {
        return archive_achievement_name;
    }

    public void setArchive_achievement_name(String archive_achievement_name) {
        this.archive_achievement_name = archive_achievement_name;
    }

    public String getArchive_achievement_description() {
        return archive_achievement_description;
    }

    public void setArchive_achievement_description(String archive_achievement_description) {
        this.archive_achievement_description = archive_achievement_description;
    }

    public String getArchive_achievement_image_path() {
        return archive_achievement_image_path;
    }

    public void setArchive_achievement_image_path(String archive_achievement_image_path) {
        this.archive_achievement_image_path = archive_achievement_image_path;
    }

    public boolean isArchive_achievement_is_deleted() {
        return archive_achievement_is_deleted;
    }

    public void setArchive_achievement_is_deleted(boolean archive_achievement_is_deleted) {
        this.archive_achievement_is_deleted = archive_achievement_is_deleted;
    }
}
