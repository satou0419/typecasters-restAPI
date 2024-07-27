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
}
