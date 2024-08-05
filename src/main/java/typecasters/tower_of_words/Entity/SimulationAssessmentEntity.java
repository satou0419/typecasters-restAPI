package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation_assessment")
public class SimulationAssessmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationAssessmentID;

    private int simulationID;

    private int totalParticipants; // total Students

    private int numberOfParticipants; // the current total takers for that simulation.

    private int missedTakers; // totalParticipants - numberOfParticipants

    private double simulationAccuracyRate; // game accuracy rate

    private double simulationDurationAverage; // game duration average

    public SimulationAssessmentEntity(
            int simulationID,
            int totalParticipants,
            int numberOfParticipants,
            int missedTakers,
            double simulationAccuracyRate,
            double simulationDurationAverage)
    {
        this.simulationID = simulationID;
        this.totalParticipants = totalParticipants;
        this.numberOfParticipants = numberOfParticipants;
        this.missedTakers = missedTakers;
        this.simulationAccuracyRate = simulationAccuracyRate;
        this.simulationDurationAverage = simulationDurationAverage;
    }
}
