package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation_words")
public class SimulationWordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationWordsID;

    private int creatorID;

    private String word;

    private String silentIndex;
}
