package typecasters.tower_of_words.DataPopulator;
import typecasters.tower_of_words.Entity.StudentEntity;
import typecasters.tower_of_words.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class StudentDataPopulator {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentDataPopulator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void populateData() {
        List<StudentEntity> students = Arrays.asList(
                new StudentEntity("Male", "Rey Dante", "Garcia", 101, 1L),
                new StudentEntity("Male", "Rey Mar", "Segalle", 102, 2L),
                new StudentEntity("Male", "Anton Joseph", "Cruz", 103, 3L),
                new StudentEntity("Male", "Gil Joshua", "Yabao", 104, 4L),
                new StudentEntity("Male", "Jhon Lorenz", "Pabroa", 105, 5L),
                new StudentEntity("Female", "Trisha Mae", "Rivera", 106, 6L)
                // Add more sample data here if needed
        );
        studentRepository.saveAll(students);
    }
}
