package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="tbl_user_details")
public class UserDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userDetailsID;
    private int userID;
    private int creditAmount;

    private int wordsCollected;
    private int achievementCount;
    private int floorCount;

    private int spellingFloorCount;
    private int syllableFloorCount;
    private int silentFloorCount;

    private String equipped_character;
    private String badge_display;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tower_progress_id")
    private UserProgressEntity userProgress;

    public UserDetailsEntity(int userID, int creditAmount, int wordsCollected, int achievementCount, int floorCount, int spellingFloorCount, int syllableFloorCount, int silentFloorCount, String equipped_character, String badge_display, UserProgressEntity userProgress) {
        this.userID = userID;
        this.creditAmount = creditAmount;
        this.wordsCollected = wordsCollected;
        this.achievementCount = achievementCount;
        this.floorCount = floorCount;
        this.spellingFloorCount = spellingFloorCount;
        this.syllableFloorCount = syllableFloorCount;
        this.silentFloorCount = silentFloorCount;
        this.equipped_character = equipped_character;
        this.badge_display = badge_display;
        this.userProgress = userProgress;
    }
}