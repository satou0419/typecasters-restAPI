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
@Table(name = "tbl_simulation_enemy_word")
public class SimulationEnemyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationEnemyID;

    private String imagePath;

    @CollectionTable(name = "tbl_simulation_enemy_words", joinColumns = @JoinColumn(name = "simulationEnemyID"))
    @Column(name = "simulationWordsID")
    private List<Integer> words = new ArrayList<>();

}
