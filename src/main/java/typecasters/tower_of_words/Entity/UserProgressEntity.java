package typecasters.tower_of_words.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_progress")
public class UserProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userProgressID;

    private int userDetailsID;

    private int spellingSectionProgress;

    private int syllableSectionProgress;

    private int silentSectionProgress;

    private int spellingFloorID;

    private int syllableFloorID;

    private int silentFloorID;

    @OneToOne(mappedBy = "userProgress")
    @JsonIgnore
    private UserDetailsEntity userDetails;


    public UserProgressEntity(int userDetailsID, int spellingSectionProgress, int syllableSectionProgress, int silentSectionProgress, int spellingFloorID, int syllableFloorID, int silentFloorID) {
        this.userDetailsID = userDetailsID;
        this.spellingSectionProgress = spellingSectionProgress;
        this.syllableSectionProgress = syllableSectionProgress;
        this.silentSectionProgress = silentSectionProgress;
        this.spellingFloorID = spellingFloorID;
        this.syllableFloorID = syllableFloorID;
        this.silentFloorID = silentFloorID;
    }
}
