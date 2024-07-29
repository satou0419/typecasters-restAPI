package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_student_word_progress")
public class StudentWordProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentWordProgressID;

    private int simulationWordsID;

    private int studentID;

    private int mistake;

    private boolean isCorrect;

    private int score;

    private int duration;

    private double accuracy;

    @ManyToOne
    @JoinColumn(name = "simulationAttemptsID")
    private SimulationAttemptsEntity simulationAttemptsID;

    public StudentWordProgressEntity(
            int simulationWordsID,
            int studentID,
            int mistake,
            boolean isCorrect,
            int score,
            int duration,
            double accuracy,
            SimulationAttemptsEntity simulationAttemptsID) {
        this.simulationWordsID = simulationWordsID;
        this.studentID = studentID;
        this.mistake = mistake;
        this.isCorrect = isCorrect;
        this.score = score;
        this.duration = duration;
        this.accuracy = accuracy;
        this.simulationAttemptsID = simulationAttemptsID;
    }
}
