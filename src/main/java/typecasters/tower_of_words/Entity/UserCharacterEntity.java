package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_character")
public class UserCharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCharacterID;

    private int userID;

    private boolean isOwned;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private CharacterEntity characterID;

    public UserCharacterEntity(int userID, boolean isOwned, CharacterEntity characterID) {
        this.userID = userID;
        this.isOwned = isOwned;
        this.characterID = characterID;
    }
}
