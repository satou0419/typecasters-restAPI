package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_adventure_syllable_enemy")
public class AdventureSyllableEnemyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adventureSyllableEnemyID;

    private int towerFloorID;

    private String imagePath;

    @ElementCollection
    @CollectionTable(name = "tbl_adventure_syllable_words", joinColumns = @JoinColumn(name = "adventure_syllable_enemy_id"))
    private List<String> words = new ArrayList<>();
}
