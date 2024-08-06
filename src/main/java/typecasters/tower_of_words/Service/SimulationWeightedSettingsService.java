package typecasters.tower_of_words.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Entity.SimulationEntity;
import typecasters.tower_of_words.Entity.SimulationWeightedSettingsEntity;
import typecasters.tower_of_words.Entity.UserEntity;
import typecasters.tower_of_words.Repository.SimulationRepository;
import typecasters.tower_of_words.Repository.SimulationWeightedSettingsRepository;
import typecasters.tower_of_words.Repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationWeightedSettingsService {

    @Autowired
    SimulationWeightedSettingsRepository simulationWeightedSettingsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimulationRepository simulationRepository;

    public SimulationWeightedSettingsEntity insertSimulationWeightedSettings(
            SimulationWeightedSettingsEntity simulationWeightedSettings,
            int simulationID,
            int creatorID)
    {
        UserEntity user = userRepository.findById(creatorID)
                .orElseThrow(() -> new NoSuchElementException("User with this ID = " + creatorID + " does not exist!"));

        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation with this ID = " + simulationID + " does not exist!"));

        if(
                (simulationWeightedSettings.getWeightedAccuracyRate()
                + simulationWeightedSettings.getWeightedTotalAttempts()
                + simulationWeightedSettings.getWeightedTotalCorrect()
                + simulationWeightedSettings.getWeightedDurationAverage()) != 1.0)
        {
            throw new IllegalArgumentException("The sum of the attributes must be equal to 1.0 or 100%");
        }
        simulationWeightedSettings.setCreatorID(user.getUserID());
        simulationWeightedSettings.setSimulationID(simulation.getSimulationID());

        return simulationWeightedSettingsRepository.save(simulationWeightedSettings);
    }

    public List<SimulationWeightedSettingsEntity> getAllSimulationWeightedSettings(){
        return simulationWeightedSettingsRepository.findAll();
    }

    public Optional<SimulationWeightedSettingsEntity> getSimulationWeightedSettingsByID(int simulationWeightedSettingsID){
        return simulationWeightedSettingsRepository.findById(simulationWeightedSettingsID);
    }

    public Optional<SimulationWeightedSettingsEntity> getSimulationWeightedSettingsByCreatorIDAndSimulationID(
            int creatorID,
            int simulationID)
    {
        UserEntity user = userRepository.findById(creatorID)
                .orElseThrow(() -> new NoSuchElementException("User with this ID = " + creatorID + " does not exist!"));

        SimulationEntity simulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation with this ID = " + simulationID + " does not exist!"));

        return simulationWeightedSettingsRepository.findOneByCreatorIDAndSimulationID(user.getUserID(), simulation.getSimulationID());
    }

    public SimulationWeightedSettingsEntity updateSimulationWeightedSettingsByCreatorIDAndSimulationID(
            SimulationWeightedSettingsEntity simulationWeightedSettings,
            int creatorID,
            int simulationID)
    {
        SimulationWeightedSettingsEntity existingSimulationWeightedSettings = getSimulationWeightedSettingsByCreatorIDAndSimulationID(creatorID, simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Weighted Settings does not exist!"));

        existingSimulationWeightedSettings.setWeightedAccuracyRate(simulationWeightedSettings.getWeightedAccuracyRate());
        existingSimulationWeightedSettings.setWeightedDurationAverage(simulationWeightedSettings.getWeightedDurationAverage());
        existingSimulationWeightedSettings.setWeightedTotalCorrect(simulationWeightedSettings.getWeightedTotalCorrect());
        existingSimulationWeightedSettings.setWeightedTotalAttempts(simulationWeightedSettings.getWeightedTotalAttempts());

        if(
                (existingSimulationWeightedSettings.getWeightedAccuracyRate()
                        + existingSimulationWeightedSettings.getWeightedTotalAttempts()
                        + existingSimulationWeightedSettings.getWeightedTotalCorrect()
                        + existingSimulationWeightedSettings.getWeightedDurationAverage()) != 1.0)
        {
            throw new IllegalArgumentException("The sum of the attributes must be equal to 1.0 or 100%");
        }else{
            return simulationWeightedSettingsRepository.save(existingSimulationWeightedSettings);
        }

    }

    public void deleteSimulationWeightedSettings(int simulationWeightedScoreID){
        if(getSimulationWeightedSettingsByID(simulationWeightedScoreID).isPresent()){
            simulationWeightedSettingsRepository.deleteById(simulationWeightedScoreID);
        }else{
            throw new NoSuchElementException("Simulation Weighted Settings ID# " + simulationWeightedScoreID + " does not exist!");
        }
    }
}
