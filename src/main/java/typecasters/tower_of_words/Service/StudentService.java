package typecasters.tower_of_words.Service;

import typecasters.tower_of_words.Entity.StudentEntity;
import typecasters.tower_of_words.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public String helloWorld(){
        return "Hello World!";
    }
}
