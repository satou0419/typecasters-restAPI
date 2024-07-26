package typecasters.tower_of_words.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation_attempts")
public class SimulationAttemptsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationAttemptsID;

    private int simulationID;

    private int simulationParticipantsID;

    private int currentAttempts;

    private double currentAccuracy;

    private int currentScore;

    private double currentDuration;

    private int currentMistakes;

    private boolean isDone;

}
