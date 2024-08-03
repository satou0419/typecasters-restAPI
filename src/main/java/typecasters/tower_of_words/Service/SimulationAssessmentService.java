package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationAssessmentEntity;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Repository.SimulationAssessmentRepository;
import typecasters.tower_of_words.Repository.SimulationRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationAssessmentService {

    @Autowired
    SimulationAssessmentRepository simulationAssessmentRepository;

    @Autowired
    SimulationRepository simulationRepository;

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

    public void deleteSimulationAssessment(int simulationAssessmentID) {
        if (getSimulationAssessmentByID(simulationAssessmentID).isPresent()){
        simulationAssessmentRepository.deleteById(simulationAssessmentID);
        }else{
            throw new NoSuchElementException("Simulation Assessment ID#" + simulationAssessmentID + " does not exist!");
        }
    }

}

