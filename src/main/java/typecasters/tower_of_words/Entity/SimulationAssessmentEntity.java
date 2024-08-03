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

    private int simulationAccuracyRate; // game accuracy rate

    private int simulationDurationAverage; // game duration average

}
