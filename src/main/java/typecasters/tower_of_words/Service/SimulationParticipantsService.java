package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SimulationParticipantsService {
    @Autowired
    SimulationParticipantsRepository simulationParticipantsRepository;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    StudentWordProgressRepository studentWordProgressRepository;

    @Autowired
    SimulationWordsRepository simulationWordsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomService roomService;

    @Transactional
    public SimulationParticipantsEntity addParticipant(SimulationParticipantsEntity participant) {
        return simulationParticipantsRepository.save(participant);
    }

    public List<SimulationParticipantsEntity> getAllParticipants() {
        return simulationParticipantsRepository.findAll();
    }

    public Optional<SimulationParticipantsEntity> getParticipantById(int id) {
        return simulationParticipantsRepository.findById(id);
    }

    public Optional<Integer> getSimulationParticipantsIDIDByUserIDAndSimulationID(Integer userID, Integer simulation) {
        SimulationEntity simulationObject = simulationRepository.findById(simulation)
                .orElseThrow(() -> new NoSuchElementException("Simulation " + simulation + " doesn't exist!"));

        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User " + userID + " doesn't exist!"));

        return simulationParticipantsRepository.findSimulationParticipantsIDByUserIDAndSimulationID(userID, simulationObject);
    }

    public Optional<SimulationParticipantsEntity> getOneByUserIDAndSimulationID(Integer userID, Integer simulationID){
        SimulationEntity simulationObject = simulationRepository.findById(simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation " + simulationID + " doesn't exist!"));

        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User " + userID + " doesn't exist!"));

        return simulationParticipantsRepository.findOneByUserIDAndSimulationID(userID, simulationObject);
    }

    public Optional<SimulationParticipantsEntity> getOneBySimulationParticipantsIDAndSimulationID(int simulationParticipantsID, int simulation){

        SimulationEntity simulationObject = simulationRepository.findById(simulation)
                .orElseThrow(() -> new NoSuchElementException("Simulation " + simulation + " doesn't exist!"));

        return simulationParticipantsRepository.findOneBySimulationParticipantsIDAndSimulationID(simulationParticipantsID, simulationObject);
    }

    @Transactional
    public SimulationParticipantsEntity updateParticipant(SimulationParticipantsEntity participant, int simulationID) {
        SimulationParticipantsEntity simulationAttempt = getOneBySimulationParticipantsIDAndSimulationID(participant.getSimulationParticipantsID(), simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation doesn't exist!"));

        simulationAttempt.setDuration(participant.getDuration());
        simulationAttempt.setScore(participant.getScore());
        simulationAttempt.setAccuracy(participant.getScore());
        simulationAttempt.setMistakes(participant.getMistakes());

        return simulationParticipantsRepository.save(simulationAttempt);
    }

    @Transactional
    public SimulationParticipantsEntity updateParticipantAttribute(int userID, int simulationID) {
        SimulationParticipantsEntity simulationParticipant = getOneByUserIDAndSimulationID(userID, simulationID)
                .orElseThrow(() -> new NoSuchElementException("Simulation Participant doesn't exist!"));

        RoomEntity room = roomService.findRoomBySimulationIDAndUserID(simulationID, simulationParticipant.getUserID())
                .orElseThrow(() -> new NoSuchElementException("Room doesn't exist!"));

        List<StudentWordProgressEntity> progressList = studentWordProgressRepository.findAllByStudentIDAndSimulationID(
                simulationParticipant.getUserID(), simulationID);

        updateSimulationParticipantFromProgress(simulationParticipant, progressList, room.getCreatorID());

        return simulationParticipantsRepository.save(simulationParticipant);
    }

    @Transactional
    private void updateSimulationParticipantFromProgress(SimulationParticipantsEntity participant, List<StudentWordProgressEntity> progressList, int creatorID) {
        int totalWords = progressList.size();

        if (totalWords == 0) {
            participant.setMistakes(0);
            participant.setScore(0);
            participant.setDuration(0);
            participant.setAccuracy(0.0);
            return;
        }

        int totalMistakes = progressList.stream().mapToInt(StudentWordProgressEntity::getMistake).sum();
        double totalScore = progressList.stream().mapToDouble(StudentWordProgressEntity::getScore).sum();
        double totalDuration = progressList.stream().mapToDouble(StudentWordProgressEntity::getDuration).sum();
        double totalAccuracy = progressList.stream().mapToDouble(StudentWordProgressEntity::getAccuracy).average().orElse(0.0);

        participant.setMistakes((((double) totalMistakes / (participant.getSimulationID().getStudentLife() * totalWords)) * 100));
        participant.setScore((int) Math.round(totalScore / totalWords));
        participant.setDuration(totalDuration / totalWords);
        participant.setAccuracy(Math.round(totalAccuracy));
        System.out.println("Check result for accuracy: ");
        participant.setDone(true);
    }

    public void deleteParticipant(int id) {
        simulationParticipantsRepository.deleteById(id);
    }

}
