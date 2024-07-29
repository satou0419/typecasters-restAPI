package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.SimulationAttemptsRepository;
import typecasters.tower_of_words.Repository.SimulationWordsRepository;
import typecasters.tower_of_words.Repository.StudentWordProgressRepository;
import typecasters.tower_of_words.Repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentWordProgressService {
    @Autowired
    StudentWordProgressRepository studentWordProgressRepository;

    @Autowired
    @Lazy
    SimulationAttemptsRepository simulationAttemptsRepository;

    @Autowired
    UserRepository userRepository;

    public StudentWordProgressEntity addProgress(StudentWordProgressEntity progress, int simulationAttemptID) {
        Optional<SimulationAttemptsEntity> attempt = simulationAttemptsRepository.findById(simulationAttemptID);
        if(attempt.isPresent()){
            SimulationAttemptsEntity attemptObject = attempt.get();
            progress.setSimulationAttemptsID(attemptObject);
            return studentWordProgressRepository.save(progress);
        }else{
            throw new NoSuchElementException("SimulationAttempt doesn't exist!");
        }

    }

    public List<StudentWordProgressEntity> getAllProgress() {
        return studentWordProgressRepository.findAll();
    }

    public Optional<StudentWordProgressEntity> getProgressById(int id) {
        return studentWordProgressRepository.findById(id);
    }

    public List<StudentWordProgressEntity> addProgress(List<StudentWordProgressEntity> progressList) {
        return studentWordProgressRepository.saveAll(progressList);
    }

    public List<StudentWordProgressEntity> getAllByStudentIDAndSimulationAttemptsID(
            int studentID,
            int simulationAttemptsID)
    {
        SimulationAttemptsEntity attempt = simulationAttemptsRepository.findById(simulationAttemptsID)
                .orElseThrow(() -> new NoSuchElementException("Current Simulation Attempt ID " + simulationAttemptsID + " does not exist!"));

        UserEntity user = userRepository.findById(studentID)
                .orElseThrow(() -> new NoSuchElementException("Student " + studentID + " doesn't exist!"));

        return studentWordProgressRepository.findAllByStudentIDAndSimulationAttemptsID(studentID, attempt);
    }

    public List<StudentWordProgressEntity> getAllBySimulationAttemptsID(
            int simulationAttemptsID)
    {
        SimulationAttemptsEntity attempt = simulationAttemptsRepository.findById(simulationAttemptsID)
                .orElseThrow(() -> new NoSuchElementException("Current Simulation Attempt ID " + simulationAttemptsID + " does not exist!"));

        return studentWordProgressRepository.findAllBySimulationAttemptsID(attempt);
    }

    public List<StudentWordProgressEntity> getAllByStudentID(int studentID){
        return studentWordProgressRepository.findAllByStudentID(studentID);
    }

    public StudentWordProgressEntity updateProgress(StudentWordProgressEntity progress, int simulationAttemptID) {
        StudentWordProgressEntity existingProgress = studentWordProgressRepository.findById(progress.getStudentWordProgressID())
                .orElseThrow(() -> new NoSuchElementException("Current Student " + progress.getStudentID() + " Word Progress " + progress.getStudentWordProgressID() + " does not exist!"));

        simulationAttemptsRepository.findById(simulationAttemptID)
                .orElseThrow(() -> new NoSuchElementException("Current Simulation Attempt ID " + simulationAttemptID + " does not exist!"));

        UserEntity user = userRepository.findById(progress.getStudentID())
                        .orElseThrow(() -> new NoSuchElementException("Current Student " + progress.getStudentID() + " doesn't exist!"));


        existingProgress.setMistake(progress.getMistake());
        existingProgress.setAccuracy(progress.getAccuracy());
        existingProgress.setScore(progress.getScore());
        existingProgress.setDuration(progress.getDuration());
        existingProgress.setCorrect(progress.isCorrect());

        return studentWordProgressRepository.save(existingProgress);
    }

    public void deleteProgress(int id) {
        studentWordProgressRepository.deleteById(id);
    }
}
