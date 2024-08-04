package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentWordProgressService {
    @Autowired
    StudentWordProgressRepository studentWordProgressRepository;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    SimulationWordsRepository simulationWordsRepository;

    public StudentWordProgressEntity addProgress(StudentWordProgressEntity progress, int simulationID) {
        Optional<SimulationEntity> ifExistSimulation = simulationRepository.findById(simulationID);
        if(ifExistSimulation.isPresent()){
            progress.setSimulationID(simulationID);
            return studentWordProgressRepository.save(progress);
        }else{
            throw new NoSuchElementException("Simulation doesn't exist!");
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

    public List<StudentWordProgressEntity> getAllByStudentIDAndSimulationID(
            int studentID,
            int simulationID)
    {
        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Current Simulation ID " + simulationID + " does not exist!"));

        UserEntity user = userRepository.findById(studentID)
                .orElseThrow(() -> new NoSuchElementException("Student " + studentID + " doesn't exist!"));

        return studentWordProgressRepository.findAllByStudentIDAndSimulationID(studentID, simulationID);
    }

    public List<StudentWordProgressEntity> getAllBySimulationID(
            int simulationID)
    {
        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Current Simulation ID " + simulationID + " does not exist!"));

        return studentWordProgressRepository.findAllBySimulationID(simulationID);
    }

    public List<StudentWordProgressEntity> getAllByStudentID(int studentID){
        return studentWordProgressRepository.findAllByStudentID(studentID);
    }

    public Optional<StudentWordProgressEntity> getOneByStudentIDAndSimulationIDAndSimulationWordsID(int studentID, int simulationID, int simulationWordsID){
        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Current Simulation ID " + simulationID + " does not exist!"));

        UserEntity user = userRepository.findById(studentID)
                .orElseThrow(() -> new NoSuchElementException("Current Student " + studentID + " doesn't exist!"));

        SimulationWordsEntity simulationWords = simulationWordsRepository.findById(simulationWordsID)
                .orElseThrow(() -> new NoSuchElementException("Current Simulation Words + " + simulationWordsID + " doesn't exist!"));

        return studentWordProgressRepository.findOneByStudentIDAndSimulationIDAndSimulationWordsID(studentID, simulationID, simulationWordsID);
    }

    public StudentWordProgressEntity updateProgress(StudentWordProgressEntity progress, int studentWordProgressID) {
        StudentWordProgressEntity existingProgress = getProgressById(studentWordProgressID)
                .orElseThrow(() -> new NoSuchElementException("Current Student " + progress.getStudentID() + " Word Progress " + progress.getStudentWordProgressID() + " does not exist!"));

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
