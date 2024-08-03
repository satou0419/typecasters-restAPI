package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation_weighted_score")
public class SimulationWeightedScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationWeightedScoreID;

    private int simulationID;

    private int simulationParticipantID; // studentID

    private int wordID;

    private double weightedScore; // A maximum of 1.00 or 100%
}
