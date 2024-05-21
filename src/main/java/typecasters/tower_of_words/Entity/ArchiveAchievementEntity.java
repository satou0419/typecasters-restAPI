package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_archive_achievement")
public class ArchiveAchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int archiveAchievementID;

    private int userID;
    private String name;
    private String description;
    private String imagePath;
    private boolean isChecked = false;
    private boolean isDeleted = false;

    public ArchiveAchievementEntity() {
    }

    public ArchiveAchievementEntity(int archiveAchievementID, int userID, String name, String description, String imagePath, boolean isChecked, boolean isDeleted) {
        this.archiveAchievementID = archiveAchievementID;
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.isChecked=isChecked;
        this.isDeleted = isDeleted;
    }

    public int getArchiveAchievementID() {
        return archiveAchievementID;
    }

    public void setArchiveAchievementID(int archiveAchievementID) {
        this.archiveAchievementID = archiveAchievementID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean check) {
        this.isChecked = check;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
