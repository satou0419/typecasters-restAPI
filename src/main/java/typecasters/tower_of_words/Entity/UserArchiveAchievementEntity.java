package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_user_archive_achievement")
public class UserArchiveAchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userArchiveAchievementID;

    private int userId;

    @ManyToOne
    @JoinColumn(name = "archive_achievement_id")
    private ArchiveAchievementEntity archiveAchievementID;

    public UserArchiveAchievementEntity() {
    }

    public UserArchiveAchievementEntity(int userArchiveAchievementID, int userId, ArchiveAchievementEntity archiveAchievementID) {
        this.userArchiveAchievementID = userArchiveAchievementID;
        this.userId = userId;
        this.archiveAchievementID = archiveAchievementID;
    }

    public int getUserArchiveAchievementID() {
        return userArchiveAchievementID;
    }

    public void setUserArchiveAchievementID(int userArchiveAchievementID) {
        this.userArchiveAchievementID = userArchiveAchievementID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArchiveAchievementEntity getArchiveAchievementID() {
        return archiveAchievementID;
    }

    public void setArchiveAchievementID(ArchiveAchievementEntity archiveAchievementID) {
        this.archiveAchievementID = archiveAchievementID;
    }
}
