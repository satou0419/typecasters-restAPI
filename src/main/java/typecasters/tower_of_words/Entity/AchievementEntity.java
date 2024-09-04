package typecasters.tower_of_words.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_achievement")
public class AchievementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int achievementID;

    private String name;

    private String description;

    private String imagePath;

    private int totalUnlocked;

    private String achievementType;

    private int criteria;

    @JsonIgnore
    @OneToMany(mappedBy = "achievementID", cascade = CascadeType.ALL)
    private List<AchievementEntity> archiveAchievement;
}
