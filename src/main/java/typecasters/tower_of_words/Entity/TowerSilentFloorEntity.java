package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_tower_silent_floor")
public class TowerSilentFloorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int towerSilentFloorID;

    private int towerSection;

    private int sectionFloor;

    private String gameType;
}
