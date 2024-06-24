package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_archive_achievement")
public class UserArchiveAchievementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userArchiveAchievementID;

    private int userID;

    @ManyToOne
    @JoinColumn(name = "archive_achievement_id")
    private ArchiveAchievementEntity archiveAchievementID;
}
