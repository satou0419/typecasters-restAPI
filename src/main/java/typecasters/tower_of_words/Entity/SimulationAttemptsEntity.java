package typecasters.tower_of_words.Entity;


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
@Table(name = "tbl_simulation_attempts")
public class SimulationAttemptsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationAttemptsID;

    private int simulationID;

//    @ManyToOne
//    @JoinColumn(name = "simulationParticipantsID")
//    private SimulationParticipantsEntity simulationParticipantsID;

    private int currentAttempt;

    private double currentAccuracy;

    private int currentScore;

    private double currentDuration;

    private boolean isDone;

//    @JsonIgnore
//    @OneToMany(mappedBy = "simulationAttemptsID", cascade = CascadeType.ALL)
//    private List<StudentWordProgressEntity> wordsProgress = new ArrayList<>();

//    public SimulationAttemptsEntity(
//            int simulationID,
////            SimulationParticipantsEntity simulationParticipantsID,
//            int currentAttempt,
//            double currentAccuracy,
//            int currentScore,
//            double currentDuration,
//            boolean isDone)
//    {
//        this.simulationID = simulationID;
////        this.simulationParticipantsID = simulationParticipantsID;
//        this.currentAttempt = currentAttempt;
//        this.currentAccuracy = currentAccuracy;
//        this.currentScore = currentScore;
//        this.currentDuration = currentDuration;
//        this.isDone = isDone;
//    }
}
