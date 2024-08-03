//package typecasters.tower_of_words.DataPopulator;
//import typecasters.tower_of_words.Entity.StudentEntity;
//import typecasters.tower_of_words.Repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.PostConstruct;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class StudentDataPopulator {
//
//    private final StudentRepository studentRepository;
//
//    @Autowired
//    public StudentDataPopulator(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @PostConstruct
//    public void populateData() {
//        List<StudentEntity> students = Arrays.asList(
//                new StudentEntity(1L, 101, "Male", "Rey Dante", "Garcia"),
//                new StudentEntity(2L, 102, "Male", "Rey Mar", "Segalle"),
//                new StudentEntity(3L, 103, "Male", "Anton Joseph", "Cruz"),
//                new StudentEntity(4L, 104, "Male", "Gil Joshua", "Yabao"),
//                new StudentEntity(5L, 105, "Male", "Jhon Lorenz", "Pabroa"),
//                new StudentEntity(6L, 106, "Female", "Trisha Mae", "Rivera")
//                // Add more sample data here if needed
//        );
//        studentRepository.saveAll(students);
//    }
//}
