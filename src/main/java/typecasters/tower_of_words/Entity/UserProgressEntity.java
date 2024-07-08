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

    private int towerSectionProgress;

    private int floorID;

    @OneToOne(mappedBy = "userProgress")
    @JsonIgnore
    private UserDetailsEntity userDetails;

    public UserProgressEntity(int userDetailsID, int towerSectionProgress, int floorID) {
        this.userDetailsID = userDetailsID;
        this.towerSectionProgress = towerSectionProgress;
        this.floorID = floorID;
    }
}
