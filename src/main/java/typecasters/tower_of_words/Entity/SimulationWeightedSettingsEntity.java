package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation_weighted_settings")
public class SimulationWeightedSettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationWeightedSettingsID;

    private int creatorID; // teacherID

    private int simulationID;

    @Column(name = "weightedAccuracyRate", nullable = false, columnDefinition = "DOUBLE DEFAULT 0.25")
    private double weightedAccuracyRate = 0.25;

    @Column(name = "weightedDurationAverage", nullable = false, columnDefinition = "DOUBLE DEFAULT 0.25")
    private double weightedDurationAverage = 0.25;

    @Column(name = "weightedTotalCorrect", nullable = false, columnDefinition = "DOUBLE DEFAULT 0.25")
    private double weightedTotalCorrect = 0.25;

    @Column(name = "weightedTotalAttempts", nullable = false, columnDefinition = "DOUBLE DEFAULT 0.25")
    private double weightedTotalAttempts = 0.25;

    public SimulationWeightedSettingsEntity(
            int creatorID,
            int simulationID,
            double weightedAccuracyRate,
            double weightedDurationAverage,
            double weightedTotalCorrect,
            double weightedTotalAttempts) {
        this.creatorID = creatorID;
        this.simulationID = simulationID;
        this.weightedAccuracyRate = weightedAccuracyRate;
        this.weightedDurationAverage = weightedDurationAverage;
        this.weightedTotalCorrect = weightedTotalCorrect;
        this.weightedTotalAttempts = weightedTotalAttempts;
    }
}
