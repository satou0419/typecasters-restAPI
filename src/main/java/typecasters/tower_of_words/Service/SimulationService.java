package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Exception.SimulationNotAvailableException;
import typecasters.tower_of_words.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationService {
    @Autowired
    SimulationRepository simulationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    SimulationAttemptsRepository simulationAttemptsRepository;

    @Autowired
    SimulationWordAssessmentService simulationWordAssessmentService;

    @Autowired
    SimulationParticipantsRepository simulationParticipantsRepository;

    @Autowired
    @Lazy
    private SimulationAttemptsService simulationAttemptsService;

    @Autowired
    @Lazy
    private StudentWordProgressService studentWordProgressService;

    @Autowired
    private SimulationWordsRepository simulationWordsRepository;

    @Autowired
    private SimulationAssessmentRepository simulationAssessmentRepository;

    @Autowired
    private SimulationWeightedSettingsService simulationWeightedSettingsService;

    @Transactional
    public SimulationEntity createSimulation(SimulationEntity simulation) {
        Optional<RoomEntity> room = roomRepository.findById(simulation.getRoomID().getRoomID());
        LocalDateTime now = LocalDateTime.now();

        if (simulation.getDeadline() == null || !now.isBefore(simulation.getDeadline())) {
            throw new SimulationNotAvailableException("Simulation cannot be created before the deadline or if the deadline is null.");
        }

        if (room.isPresent()) {

            simulation = simulationRepository.save(simulation);

            for (Integer i : room.get().getMembers()) {
                SimulationParticipantsEntity user = new SimulationParticipantsEntity(
                        i, // userID
                        0, // mistakes
                        0, // score
                        0, // duration
                        0, // accuracy
                        false, // isDone
                        simulation
                );

                user = simulationParticipantsRepository.save(user);
                simulation.addParticipants(user);

                SimulationAssessmentEntity simulationAssessment = new SimulationAssessmentEntity(
                        simulation.getSimulationID(),
                        0,  // initial totalParticipants
                        0,  // initial numberOfParticipants
                        0,  // initial missedTakers
                        0.0, // initial simulationAccuracyRate
                        0.0  // initial simulationDurationAverage
                );

                SimulationAssessmentEntity existingAssessment = simulationAssessmentRepository.findOneBySimulationID(simulation.getSimulationID())
                        .orElse(simulationAssessment);
                existingAssessment.setTotalParticipants(existingAssessment.getTotalParticipants() + 1);
                simulationAssessmentRepository.save(existingAssessment);
            }
        } else {
            throw new NoSuchElementException("Room does not exist!");
        }

        List<SimulationWordAssessmentEntity> assessments = new ArrayList<>();
        for (SimulationEnemyEntity enemy : simulation.getEnemy()) {
            for (Integer wordID : enemy.getWords()) {
                SimulationWordsEntity word = simulationWordsRepository.findById(wordID)
                        .orElseThrow(() -> new NoSuchElementException("Word " + wordID + " does not exist!"));

                assessments.add(new SimulationWordAssessmentEntity(
                        simulation.getSimulationID(),
                        enemy.getSimulationEnemyID(),
                        word.getSimulationWordsID(),
                        0,  // Accuracy
                        0,  // Attempts
                        0,  // Score
                        0   // Duration
                ));
            }
        }
        simulationWordAssessmentService.addWordAssessments(assessments);

        List<StudentWordProgressEntity> progressList = new ArrayList<>();
        for (SimulationWordAssessmentEntity assessment : assessments) {
            for (SimulationParticipantsEntity participant : simulation.getParticipants()) {
                progressList.add(new StudentWordProgressEntity(
                        assessment.getSimulationWordID(),
                        participant.getUserID(),
                        simulation.getSimulationID(),
                        0, // Mistake
                        false, // Correct
                        0, // Score
                        0, // Duration
                        0 // Accuracy
                ));
            }
        }
        studentWordProgressService.addProgress(progressList);

        Optional<SimulationWeightedSettingsEntity> optionalWeightedSettings = simulationWeightedSettingsService.getSimulationWeightedSettingsByCreatorIDAndSimulationID(
                room.get().getCreatorID(), simulation.getSimulationID());

        SimulationWeightedSettingsEntity simulationWeightedSettings;

        if (optionalWeightedSettings.isPresent()) {
            simulationWeightedSettings = optionalWeightedSettings.get();
        } else {

            simulationWeightedSettings = new SimulationWeightedSettingsEntity(
                    simulation.getSimulationID(),
                    room.get().getCreatorID(),
                    0.25,
                    0.25,
                    0.25,
                    0.25
            );
            simulationWeightedSettingsService.insertSimulationWeightedSettings(simulationWeightedSettings, simulation.getSimulationID(), room.get().getCreatorID());
        }

        SimulationAssessmentEntity simulationAssessment = simulationAssessmentRepository.findOneBySimulationID(simulation.getSimulationID())
                .orElse(new SimulationAssessmentEntity(
                        simulation.getSimulationID(),
                        simulation.getParticipants().size(), // totalParticipants
                        0, // numberOfParticipants
                        0, // missedTakers
                        0.0, // simulationAccuracyRate
                        0.0  // simulationDurationAverage
                ));
        simulationAssessment.setTotalParticipants(simulation.getParticipants().size());
        simulationAssessmentRepository.save(simulationAssessment);

        return simulation;
    }

    public Optional<SimulationEntity> findByID(int simulationID){
        return simulationRepository.findById(simulationID);
    }

    public SimulationEntity insertWord(SimulationEntity simulation, SimulationEnemyEntity word) {
        simulation.addWord(word);

        return simulation;
    }

    public List<SimulationEntity> roomSimulations(int roomID) {
        RoomEntity room = roomRepository.findById(roomID).get();
        return simulationRepository.findAllByRoomID(room);
    }

    public Optional<SimulationEntity> simulationDetails(int simulationID) {
        return simulationRepository.findById(simulationID);
    }

    public List<SimulationEntity> studentSimulations(Integer userID) {
        List<SimulationEntity> simulationList = new ArrayList<>();
        try {
            List<SimulationEntity> simulation = simulationRepository.findAll();

            for(SimulationEntity i : simulation) {
                for(SimulationParticipantsEntity j : i.getParticipants()) {
                    if(userID.equals(j.getUserID())) {
                        simulationList.add(i);
                    }
                }
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User " + userID + " does not exist");
        }
        return simulationList;
    }

    public SimulationEntity renameSimulation(SimulationEntity simulation) {
        SimulationEntity edit = new SimulationEntity();

        try {
            edit = simulationRepository.findById(simulation.getSimulationID()).get();

            edit.setName(simulation.getName());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Simulation " + simulation.getSimulationID() + " does not exist");
        }finally {
            return simulationRepository.save(edit);
        }
    }

    public SimulationEntity editDeadlineSimulation(SimulationEntity simulation, int simulationID) {

        LocalDateTime now = LocalDateTime.now();

        SimulationEntity edit = findByID(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation doesn't exist!"));

        if (simulation.getDeadline() == null || !now.isBefore(simulation.getDeadline())) {
            throw new SimulationNotAvailableException("Simulation cannot be created before the deadline or if the deadline is null.");
        }

            edit.setName(simulation.getName());
            edit.setDeadline(simulation.getDeadline());

            return simulationRepository.save(edit);

    }

    public String removeSimulation(int simulationID) {
        String msg = "";

        if(simulationRepository.findById(simulationID).isPresent()) {
            simulationRepository.deleteById(simulationID);

            msg = "Item " + simulationID + " is successfully deleted!";
        }

        return msg;
    }

    @Transactional
    public SimulationEntity cloneSimulation(int simulationID, int targetRoomID) {

        SimulationEntity originalSimulation = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation not found with ID: " + simulationID));

        RoomEntity targetRoom = roomRepository.findById(targetRoomID)
                .orElseThrow(() -> new NoSuchElementException("Room not found with ID: " + targetRoomID));

        SimulationEntity clonedSimulation = new SimulationEntity();
        clonedSimulation.setRoomID(targetRoom);
        clonedSimulation.setSimulationType(originalSimulation.getSimulationType());
        clonedSimulation.setName(originalSimulation.getName() + " - Clone");
        clonedSimulation.setDeadline(originalSimulation.getDeadline());
        clonedSimulation.setAttackInterval(originalSimulation.getAttackInterval());
        clonedSimulation.setStudentLife(originalSimulation.getStudentLife());
        clonedSimulation.setItems(originalSimulation.isItems());
        clonedSimulation.setDescription(originalSimulation.isDescription());
        clonedSimulation.setPronunciation(originalSimulation.isPronunciation());

        for (SimulationEnemyEntity enemy : originalSimulation.getEnemy()) {
            SimulationEnemyEntity clonedEnemy = new SimulationEnemyEntity();
            clonedEnemy.setImagePath(enemy.getImagePath());
            clonedEnemy.setWords(new ArrayList<>(enemy.getWords()));
            clonedSimulation.addWord(clonedEnemy);
        }

        List<SimulationParticipantsEntity> newParticipants = new ArrayList<>();
        for (Integer userID : targetRoom.getMembers()) {
            SimulationParticipantsEntity newParticipant = new SimulationParticipantsEntity(
                    userID,
                    0, // mistakes
                    0, // score
                    0, // duration
                    0, // accuracy
                    false, // isDone
                    clonedSimulation
            );
            newParticipants.add(newParticipant);
        }
        clonedSimulation.setParticipants(newParticipants);

        clonedSimulation = simulationRepository.save(clonedSimulation);

        List<SimulationWordAssessmentEntity> assessments = new ArrayList<>();
        for (SimulationEnemyEntity enemy : clonedSimulation.getEnemy()) {
            for (Integer wordID : enemy.getWords()) {
                SimulationWordsEntity word = simulationWordsRepository.findById(wordID)
                        .orElseThrow(() -> new NoSuchElementException("Word " + wordID + " does not exist!"));

                assessments.add(new SimulationWordAssessmentEntity(
                        clonedSimulation.getSimulationID(),
                        enemy.getSimulationEnemyID(),
                        word.getSimulationWordsID(),
                        0,  // Accuracy
                        0,  // Mistakes
                        0,  // Score
                        0   // Duration
                ));
            }
        }
        simulationWordAssessmentService.addWordAssessments(assessments);

        List<StudentWordProgressEntity> progressList = new ArrayList<>();
        for (SimulationWordAssessmentEntity assessment : assessments) {
            for (SimulationParticipantsEntity participant : clonedSimulation.getParticipants()) {
                progressList.add(new StudentWordProgressEntity(
                        assessment.getSimulationWordID(),
                        participant.getUserID(),
                        clonedSimulation.getSimulationID(),
                        0, // Mistake
                        false, // Correct
                        0, // Score
                        0, // Duration
                        0 // Accuracy
                ));
            }
        }
        studentWordProgressService.addProgress(progressList);

        SimulationAssessmentEntity simulationAssessment = new SimulationAssessmentEntity(
                clonedSimulation.getSimulationID(),
                clonedSimulation.getParticipants().size(), // totalParticipants
                0, // numberOfParticipants
                0, // missedTakers
                0.0, // simulationAccuracyRate
                0.0  // simulationDurationAverage
        );
        simulationAssessmentRepository.save(simulationAssessment);

        Optional<SimulationWeightedSettingsEntity> originalWeightedSettingsOpt = simulationWeightedSettingsService.getSimulationWeightedSettingsByCreatorIDAndSimulationID(
                originalSimulation.getRoomID().getCreatorID(), originalSimulation.getSimulationID());

        if (originalWeightedSettingsOpt.isPresent()) {
            SimulationWeightedSettingsEntity originalWeightedSettings = originalWeightedSettingsOpt.get();
            SimulationWeightedSettingsEntity clonedWeightedSettings = new SimulationWeightedSettingsEntity(
                    clonedSimulation.getSimulationID(),
                    targetRoom.getCreatorID(),
                    originalWeightedSettings.getWeightedAccuracyRate(),
                    originalWeightedSettings.getWeightedDurationAverage(),
                    originalWeightedSettings.getWeightedTotalCorrect(),
                    originalWeightedSettings.getWeightedTotalAttempts()
            );

            simulationWeightedSettingsService.insertSimulationWeightedSettings(
                    clonedWeightedSettings,
                    clonedSimulation.getSimulationID(),
                    targetRoom.getCreatorID());

        }

        return clonedSimulation;
    }

}
