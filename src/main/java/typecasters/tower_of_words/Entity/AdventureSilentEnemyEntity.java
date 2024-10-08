package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import typecasters.tower_of_words.WordIndex;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_adventure_silent_enemy")
public class AdventureSilentEnemyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adventureEnemyID;

    private int towerFloorID;

    private String imagePath;

    @ElementCollection
    @CollectionTable(name = "tbl_adventure_silent_words", joinColumns = @JoinColumn(name = "adventure_enemy_id"))
    private List<WordIndex> words = new ArrayList<>();
}
