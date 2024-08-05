package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.SimulationAssessmentRepository;
import typecasters.tower_of_words.Repository.SimulationParticipantsRepository;
import typecasters.tower_of_words.Repository.SimulationRepository;
import typecasters.tower_of_words.Repository.SimulationWordAssessmentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationAssessmentService {

    @Autowired
    SimulationAssessmentRepository simulationAssessmentRepository;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    SimulationWordAssessmentRepository simulationWordAssessmentRepository;

    @Autowired
    SimulationParticipantsRepository simulationParticipantsRepository;

    public SimulationAssessmentEntity insertSimulationWord(
            SimulationAssessmentEntity simulationAssessment,
            int simulationID)
    {
        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation with this ID = " + simulationID + " does not exist!"));

        simulationAssessment.setSimulationID(simulation.getSimulationID());

        return simulationAssessmentRepository.save(simulationAssessment);
    }

    public List<SimulationAssessmentEntity> getAllSimulationAssessment(){
        return simulationAssessmentRepository.findAll();
    }

    public Optional<SimulationAssessmentEntity> getSimulationAssessmentByID(int simulationAssessmentID){
        return simulationAssessmentRepository.findById(simulationAssessmentID);
    }

    public Optional<SimulationAssessmentEntity> getSimulationAssessmentBySimulationID(int simulationID){
        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation with this ID = " + simulationID + " does not exist!"));

        return simulationAssessmentRepository.findOneBySimulationID(simulation.getSimulationID());
    }

    public SimulationAssessmentEntity updateSimulationAssessmentBySimulationID(
            SimulationAssessmentEntity simulationAssessment,
            int simulationID,
            int simulationAssessmentID)
    {
        SimulationAssessmentEntity existingSimulationAssessment = simulationAssessmentRepository.findOneBySimulationIDANDSimulationAssessmentID(simulationID, simulationAssessmentID)
                        .orElseThrow(() -> new NoSuchElementException("Simulation Assessment does not exist!"));

        existingSimulationAssessment.setTotalParticipants(simulationAssessment.getTotalParticipants());
        existingSimulationAssessment.setNumberOfParticipants(simulationAssessment.getNumberOfParticipants());
        existingSimulationAssessment.setMissedTakers(simulationAssessment.getMissedTakers());
        existingSimulationAssessment.setSimulationAccuracyRate(simulationAssessment.getSimulationAccuracyRate());
        existingSimulationAssessment.setSimulationDurationAverage(simulationAssessment.getSimulationDurationAverage());

        return simulationAssessmentRepository.save(existingSimulationAssessment);

    }

    @Transactional
    public SimulationAssessmentEntity updateSimulationAssessment(int simulationID){
        SimulationAssessmentEntity existingSimulationAssessment = simulationAssessmentRepository.findOneBySimulationID(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Assessment does not exist!"));

        List<SimulationWordAssessmentEntity> wordAssessmentList = simulationWordAssessmentRepository.findAllBySimulationID(simulationID);

        updateSimulationAssessmentFromProgress(existingSimulationAssessment, wordAssessmentList, simulationID);

        return simulationAssessmentRepository.save(existingSimulationAssessment);
    }

    @Transactional
    private void updateSimulationAssessmentFromProgress(
            SimulationAssessmentEntity simulationAssessment,
            List<SimulationWordAssessmentEntity> wordAssessmentList,
            int simulationID)
    {
        int totalStudents = simulationAssessment.getTotalParticipants();

        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation ID# " + simulationID + " doesn't exist!"));

        List<SimulationParticipantsEntity> participants = simulationParticipantsRepository.findAllBySimulationID(simulation);

        int numberOfParticipants = (int) participants.stream().filter(SimulationParticipantsEntity::isDone).count();
        int missedTakers = totalStudents - numberOfParticipants;

        if(totalStudents < 0){
            simulationAssessment.setSimulationAccuracyRate(0);
            simulationAssessment.setMissedTakers(0);
            simulationAssessment.setSimulationDurationAverage(0);
            return;
        }

        double totalDuration = wordAssessmentList.stream().mapToDouble(SimulationWordAssessmentEntity::getDuration).sum();
        double totalAccuracy = wordAssessmentList.stream().mapToDouble(SimulationWordAssessmentEntity::getAccuracy).average().orElse(0.0);

        double averageDuration = totalDuration / participants.size();
        double averageAccuracy = totalAccuracy / participants.size();

        simulationAssessment.setSimulationAccuracyRate(averageAccuracy);
        simulationAssessment.setMissedTakers(missedTakers);
        simulationAssessment.setSimulationDurationAverage(averageDuration);

        // Update the number of participants
        simulationAssessment.setNumberOfParticipants(numberOfParticipants);

    }


    public void deleteSimulationAssessment(int simulationAssessmentID) {
        if (getSimulationAssessmentByID(simulationAssessmentID).isPresent()){
            simulationAssessmentRepository.deleteById(simulationAssessmentID);
        }else{
            throw new NoSuchElementException("Simulation Assessment ID#" + simulationAssessmentID + " does not exist!");
        }
    }
}

