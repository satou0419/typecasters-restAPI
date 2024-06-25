package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation_participants")
public class SimulationParticipantsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationParticipantsID;

    private int userID;

    private int score;

    private String time;

    private int attempts;

    private int accuracy;

    private boolean isDone;
}
