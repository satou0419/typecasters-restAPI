package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationWordsEntity;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Repository.SimulationWordsRepository;
import typecasters.tower_of_words.Repository.StudentWordProgressRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentWordProgressService {
    @Autowired
    StudentWordProgressRepository studentWordProgressRepository;

    public StudentWordProgressEntity addProgress(StudentWordProgressEntity progress) {
        return studentWordProgressRepository.save(progress);
    }

    public List<StudentWordProgressEntity> getAllProgress() {
        return studentWordProgressRepository.findAll();
    }

    public Optional<StudentWordProgressEntity> getProgressById(int id) {
        return studentWordProgressRepository.findById(id);
    }

    public List<StudentWordProgressEntity> getAllByStudentID(int studentID){
        return studentWordProgressRepository.findAllByStudentID(studentID);
    }

    public StudentWordProgressEntity setProgress(StudentWordProgressEntity progress) {
        StudentWordProgressEntity edit = new StudentWordProgressEntity();
        try {
            edit = studentWordProgressRepository.findById(progress.getStudentWordProgressID()).get();

            edit.setMistake(progress.getMistake());
            edit.setCorrect(progress.isCorrect());
            edit.setScore(progress.getScore());
            edit.setDuration(progress.getDuration());
            edit.setAccuracy(progress.getAccuracy());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Simulation Word " + progress.getStudentWordProgressID() + " does not exist");
        }

        return studentWordProgressRepository.save(edit);
    }

    public void deleteProgress(int id) {
        studentWordProgressRepository.deleteById(id);
    }
}
