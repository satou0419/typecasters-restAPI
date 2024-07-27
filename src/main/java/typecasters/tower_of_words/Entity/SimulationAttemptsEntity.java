package typecasters.tower_of_words.Entity;


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

    private int simulationParticipantsID;

    private int currentAttempts;

    private double currentAccuracy;

    private int currentScore;

    private double currentDuration;

    private boolean isDone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_student_words_progress",
            joinColumns = @JoinColumn(name = "simulationParticipantsID"),
            inverseJoinColumns = @JoinColumn(name = "studentWordProgressID")
    )
    private List<StudentWordProgressEntity> wordsProgress = new ArrayList<>();
}
