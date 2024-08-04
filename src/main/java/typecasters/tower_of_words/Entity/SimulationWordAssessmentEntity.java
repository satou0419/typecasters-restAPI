package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation_word_assessment")
public class SimulationWordAssessmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationWordAssessmentID;

    private int simulationID;

    private int simulationEnemyID;

    private int simulationWordID;

    private double accuracy;

    private double mistake;

    private int score;

    private int duration;

    public SimulationWordAssessmentEntity(
            int simulationID,
            int simulationEnemyID,
            int simulationWordID,
            double accuracy,
            double mistake,
            int score,
            int duration)
    {
        this.simulationID = simulationID;
        this.simulationEnemyID = simulationEnemyID;
        this.simulationWordID = simulationWordID;
        this.accuracy = accuracy;
        this.mistake = mistake;
        this.score = score;
        this.duration = duration;
    }
}
