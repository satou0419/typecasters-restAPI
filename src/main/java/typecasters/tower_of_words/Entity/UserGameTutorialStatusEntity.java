package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_game_tutorial_status")
public class UserGameTutorialStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userGameTutorialStatusID;

    private int userDetailsID;

    @Column(name = "isFirstGameSyllableAdventure", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isFirstGameSyllableAdventure = false;

    @Column(name = "isFirstGameSilentAdventure", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isFirstGameSilentAdventure = false;

    @Column(name = "isFirstGameSpellingAdventure", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isFirstGameSpellingAdventure = false;

    @Column(name = "isFirstGamePlayground", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isFirstGamePlayground = false;

    @Column(name = "isFirstGameSyllableSimu", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isFirstGameSyllableSimu = false;

    @Column(name = "isFirstGameSilentSimu", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isFirstGameSilentSimu = false;

    @Column(name = "isFirstGameSpellingSimu", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isFirstGameSpellingSimu = false;

    public UserGameTutorialStatusEntity(int userDetailsID) {
        this.userDetailsID = userDetailsID;
    }
}
