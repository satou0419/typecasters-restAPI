package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private Date unlockedDate;

    @ManyToOne
    @JoinColumn(name = "achievementID")
    private AchievementEntity achievementID;
}
