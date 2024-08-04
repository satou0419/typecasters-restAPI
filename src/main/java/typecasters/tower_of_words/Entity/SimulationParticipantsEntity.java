package typecasters.tower_of_words.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tbl_simulation_participants")
public class SimulationParticipantsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationParticipantsID;

    private int userID;

//    @JsonIgnore
//    @OneToMany(mappedBy = "simulationParticipantsID", cascade = CascadeType.ALL)
//    private List<SimulationAttemptsEntity> attempts;
//
//    private int currentAttempts;

    private double mistakes;

    private int score;

    private double duration;

    private double accuracy;

    private boolean isDone;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "simulationID")
    private SimulationEntity simulationID;

    public SimulationParticipantsEntity(
            int userID,
            double mistakes,
            int score,
            double duration,
            double accuracy,
            boolean isDone,
            SimulationEntity simulationID) {
        this.userID = userID;
        this.mistakes = mistakes;
        this.score = score;
        this.duration = duration;
        this.accuracy = accuracy;
        this.isDone = isDone;
        this.simulationID = simulationID;
    }
}
