package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationWordAssessmentEntity;
import typecasters.tower_of_words.Entity.SimulationWordsEntity;
import typecasters.tower_of_words.Entity.StudentWordProgressEntity;
import typecasters.tower_of_words.Repository.SimulationRepository;
import typecasters.tower_of_words.Repository.SimulationWordAssessmentRepository;
import typecasters.tower_of_words.Repository.SimulationWordsRepository;
import typecasters.tower_of_words.Repository.StudentWordProgressRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class SimulationWordAssessmentService {
    @Autowired
    SimulationWordAssessmentRepository simulationWordAssessmentRepository;

    @Autowired
    @Lazy
    SimulationWordsRepository simulationWordsRepository;

    @Autowired
    @Lazy
    SimulationRepository simulationRepository;

    @Autowired
    StudentWordProgressRepository studentWordProgressRepository;

    public SimulationWordAssessmentEntity addWordAssessment(SimulationWordAssessmentEntity word) {
        return simulationWordAssessmentRepository.save(word);
    }

    public List<SimulationWordAssessmentEntity> getAllWordAssessment() {
        return simulationWordAssessmentRepository.findAll();
    }

    public Optional<SimulationWordAssessmentEntity> getWordAssessmentById(int id) {
        return simulationWordAssessmentRepository.findById(id);
    }

    public List<SimulationWordAssessmentEntity> getAllBySimulationID(int simulationID){
        return simulationWordAssessmentRepository.findAllBySimulationID(simulationID);
    }

    public Optional<SimulationWordAssessmentEntity> getWordAsessmentBySimulationIDAndSimulationWordID(
            int simulationID,
            int simulationWordID)
    {

        SimulationWordsEntity simulationWord = simulationWordsRepository.findById(simulationWordID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Word ID# " + simulationWordID + " doesn't exist!"));

        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation ID# " + simulationID + " does not exist!"));

        return simulationWordAssessmentRepository.findOneBySimulationIDANDSimulationWordID(simulation.getSimulationID(), simulationWord.getSimulationWordsID());

    }

    @Transactional
    public SimulationWordAssessmentEntity setWordAssessment(SimulationWordAssessmentEntity word, int simulationID, int simulationWordID) {
        SimulationWordAssessmentEntity edit = getWordAsessmentBySimulationIDAndSimulationWordID(simulationID, simulationWordID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Word Assessment doesn't exist!"));

        edit.setAccuracy(word.getAccuracy());
        edit.setMistake(word.getMistake());
        edit.setDuration(word.getDuration());
        edit.setScore(word.getScore());

        return simulationWordAssessmentRepository.save(edit);
    }

    @Transactional
    public void updateAssessmentAggregates(int simulationID) {
        List<SimulationWordAssessmentEntity> assessments = getAllBySimulationID(simulationID);
        Map<Integer, List<StudentWordProgressEntity>> progressMap = getProgressMap(simulationID);

        updateAssessments(assessments, progressMap);
    }

    private Map<Integer, List<StudentWordProgressEntity>> getProgressMap(int simulationID) {
        List<StudentWordProgressEntity> progressList = studentWordProgressRepository.findAllBySimulationID(simulationID);
        return progressList.stream()
                .collect(Collectors.groupingBy(StudentWordProgressEntity::getSimulationWordsID));
    }

    private void updateAssessments(List<SimulationWordAssessmentEntity> assessments,
                                   Map<Integer, List<StudentWordProgressEntity>> progressMap) {
        for (SimulationWordAssessmentEntity assessment : assessments) {
            List<StudentWordProgressEntity> wordProgressList = progressMap.getOrDefault(assessment.getSimulationWordID(), new ArrayList<>());
            updateAssessmentEach(assessment, wordProgressList);
        }
    }

    private void updateAssessmentEach(SimulationWordAssessmentEntity assessment,
                                           List<StudentWordProgressEntity> wordProgressList) {
        int totalMistake = 0;
        int totalScore = 0;
        int totalDuration = 0;
        int totalAccuracy = 0;

        for (StudentWordProgressEntity progress : wordProgressList) {
            totalMistake += progress.getMistake();
            totalScore += progress.getScore();
            totalDuration += progress.getDuration();
            totalAccuracy += progress.getAccuracy();
        }

        int totalStudents = wordProgressList.size();
        if (totalStudents > 0) {
            assessment.setMistake((double) totalMistake / totalStudents);
            assessment.setScore(totalScore / totalStudents);
            assessment.setDuration(totalDuration / totalStudents);
            assessment.setAccuracy((double) totalAccuracy / totalStudents);
        } else {
            assessment.setMistake(0);
            assessment.setScore(0);
            assessment.setDuration(0);
            assessment.setAccuracy(0);
        }

        simulationWordAssessmentRepository.save(assessment);
    }

    public List<SimulationWordAssessmentEntity> addWordAssessments(List<SimulationWordAssessmentEntity> words) {
        return simulationWordAssessmentRepository.saveAll(words);
    }

    public void deleteWordAssessment(int id) {
        simulationWordAssessmentRepository.deleteById(id);
    }
}
