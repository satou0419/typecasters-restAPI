package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import typecasters.tower_of_words.Repository.SimulationAttemptsRepository;
import typecasters.tower_of_words.Repository.SimulationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SimulationRepository simulationRepository;

    @Autowired
    SimulationAttemptsRepository simulationAttemptsRepository;

    @Autowired
    SimulationWordAssessmentService simulationWordAssessmentService;

    public RoomEntity createRoom(RoomEntity room) {
        room.setCode(generateUniqueCode());
        return roomRepository.save(room);
    }

    private String generateUniqueCode() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd-HHmm-ss");
        String timestamp = formatter.format(now);

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder codeBuilder = new StringBuilder(timestamp);
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }

//    public RoomEntity insertStudent(Integer userID, int roomID) {
//        RoomEntity room = roomRepository.findById(roomID).get();
//        List<SimulationEntity> simulations = simulationRepository.findAllByRoomID(room);
//        try {
//            for(Integer i : room.getMembers()){
//                if(i.equals(userID)){
//                    throw new IllegalArgumentException("User " + userID + " already exists in the room");
//                }
//            }
//            room.addMembers(userID);
//
//            for (SimulationEntity simulation : simulations) {
//                SimulationParticipantsEntity participant = new SimulationParticipantsEntity();
//                participant.setUserID(userID);
//                simulation.addParticipants(participant);
//                simulationRepository.save(simulation);
//            }
//
//        }catch(NoSuchElementException ex) {
//            throw new NoSuchElementException ("User " + userID + " does not exist");
//        }
//        return roomRepository.save(room);
//
//    }

    @Transactional
    public RoomEntity insertStudent(Integer userID, int roomID) {
        RoomEntity room = roomRepository.findById(roomID)
                .orElseThrow(() -> new NoSuchElementException("Room " + roomID + " does not exist"));
        List<SimulationEntity> simulations = simulationRepository.findAllByRoomID(room);

        if (room.getMembers().contains(userID)) {
            throw new IllegalArgumentException("User " + userID + " already exists in the room");
        }
        room.addMembers(userID);

        // Prepare lists for batch saving
        List<SimulationAttemptsEntity> attemptsList = new ArrayList<>();
        List<SimulationWordAssessmentEntity> assessmentsList = new ArrayList<>();

        for (SimulationEntity simulation : simulations) {
            SimulationParticipantsEntity participant = new SimulationParticipantsEntity();
            participant.setUserID(userID);
            simulation.addParticipants(participant);

            // Add SimulationAttempts based on numberOfAttempts
            int numberOfAttempts = simulation.getNumberOfAttempt();
            for (int attemptNumber = 1; attemptNumber <= numberOfAttempts; attemptNumber++) {
                SimulationAttemptsEntity attempt = getSimulationAttemptsEntity(userID, simulation, attemptNumber);
                attemptsList.add(attempt);
            }

            // Add SimulationWordAssessment if it does not exist
            for (SimulationEnemyEntity enemy : simulation.getEnemy()) {
                for (SimulationWordsEntity word : enemy.getWords()) {
                    SimulationWordAssessmentEntity assessment = getSimulationWordAssessmentEntity(simulation, enemy, word);
                    assessmentsList.add(assessment);
                }
            }
            simulation.getAssessment().addAll(assessmentsList);
            simulationRepository.save(simulation);
        }

        // Batch save attempts and assessments
        simulationAttemptsRepository.saveAll(attemptsList);
        simulationWordAssessmentService.addWordAssessments(assessmentsList);

        return roomRepository.save(room);
    }


    private static SimulationAttemptsEntity getSimulationAttemptsEntity(Integer userID, SimulationEntity simulation, int attemptNumber) {
        SimulationAttemptsEntity attempt = new SimulationAttemptsEntity();
        attempt.setSimulationID(simulation.getSimulationID());
        attempt.setSimulationParticipantsID(userID);
        attempt.setCurrentAttempts(attemptNumber);
        attempt.setCurrentAccuracy(0);
        attempt.setCurrentScore(0);
        attempt.setCurrentDuration(0);
        attempt.setDone(false);
        return attempt;
    }

    private static SimulationWordAssessmentEntity getSimulationWordAssessmentEntity(SimulationEntity simulation, SimulationEnemyEntity enemy, SimulationWordsEntity word) {
        SimulationWordAssessmentEntity assessment = new SimulationWordAssessmentEntity();
        assessment.setSimulationID(simulation.getSimulationID());
        assessment.setSimulationEnemyID(enemy.getSimulationEnemyID());
        assessment.setSimulationWordID(word.getSimulationWordsID());
        assessment.setAccuracy(0);
        assessment.setAttempts(0);
        assessment.setScore(0);
        assessment.setDuration(0);
        return assessment;
    }

//    public RoomEntity joinRoom(Integer userID, String code) {
//        RoomEntity room = roomRepository.findByCode(code);
//        List<SimulationEntity> simulations = simulationRepository.findAllByRoomID(room);
//        try {
//
////            room = roomRepository.findByCode(code);
//            for(Integer i : room.getMembers()){
//                if(i.equals(userID)){
//                    throw new IllegalArgumentException("User " + userID + " already exists in the room");
//                }
//            }
//
//            room.addMembers(userID);
//
//            for (SimulationEntity simulation : simulations) {
//                SimulationParticipantsEntity participant = new SimulationParticipantsEntity();
//                participant.setUserID(userID);
//                simulation.addParticipants(participant);
//                simulationRepository.save(simulation);
//            }
//
//        }catch(NoSuchElementException ex) {
//            throw new NoSuchElementException ("User " + userID + " does not exist");
//        }
//
//        return roomRepository.save(room);
//    }

    @Transactional
    public RoomEntity joinRoom(Integer userID, String code) {
        RoomEntity room = roomRepository.findByCode(code);
        List<SimulationEntity> simulations = simulationRepository.findAllByRoomID(room);

        if (room.getMembers().contains(userID)) {
            throw new IllegalArgumentException("User " + userID + " already exists in the room");
        }
        room.addMembers(userID);

        // Prepare lists for batch saving
        List<SimulationAttemptsEntity> attemptsList = new ArrayList<>();
        List<SimulationWordAssessmentEntity> assessmentsList = new ArrayList<>();

        for (SimulationEntity simulation : simulations) {
            SimulationParticipantsEntity participant = new SimulationParticipantsEntity();
            participant.setUserID(userID);
            simulation.addParticipants(participant);

            // Add SimulationAttempts based on numberOfAttempts
            int numberOfAttempts = simulation.getNumberOfAttempt();
            for (int attemptNumber = 1; attemptNumber <= numberOfAttempts; attemptNumber++) {
                SimulationAttemptsEntity attempt = getSimulationAttemptsEntity(userID, simulation, attemptNumber);
                attemptsList.add(attempt);
            }

            // Add SimulationWordAssessment if it does not exist
            for (SimulationEnemyEntity enemy : simulation.getEnemy()) {
                for (SimulationWordsEntity word : enemy.getWords()) {
                    SimulationWordAssessmentEntity assessment = getSimulationWordAssessmentEntity(simulation, enemy, word);
                    assessmentsList.add(assessment);
                }
            }
            simulation.getAssessment().addAll(assessmentsList);
            simulationRepository.save(simulation);
        }

        // Batch save attempts and assessments
        simulationAttemptsRepository.saveAll(attemptsList);
        simulationWordAssessmentService.addWordAssessments(assessmentsList);

        return roomRepository.save(room);
    }


    public List<RoomEntity> viewCreatedRooms(int creatorID) {
        return roomRepository.findAllByCreatorID(creatorID);
    }

    public RoomEntity roomsDetailsByCode(String code) {
        return roomRepository.findByCode(code);
    }

    public Optional<RoomEntity> roomsDetailsByID(int roomID) {
        return roomRepository.findById(roomID);
    }

    public List<SimulationEntity> roomSimulations(int roomID) {
        RoomEntity room = roomRepository.findById(roomID).orElseThrow(() -> new NoSuchElementException("Room " + roomID + " does not exist"));
        return room.getSimulations();
    }

    public List<RoomEntity> studentRooms(Integer userID) {
        List<RoomEntity> roomList = new ArrayList<>();
        try {
            List<RoomEntity> room = roomRepository.findAll();

            for(RoomEntity i : room) {
                for(Integer j : i.getMembers()) {
                    if(j.equals(userID)) {
                        roomList.add(i);
                    }
                }
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("User " + userID + " does not exist");
        }
        return roomList;
    }

    public List<Integer> roomStudents(int roomId) {
        RoomEntity room = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException("Room " + roomId + " does not exist"));
        return room.getMembers();
    }

    public RoomEntity renameRoom(RoomEntity room) {
        RoomEntity edit = new RoomEntity();

        try {
            edit = roomRepository.findById(room.getRoomID()).get();

            edit.setName(room.getName());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Room " + room.getRoomID() + " does not exist");
        }finally {
            return roomRepository.save(edit);
        }
    }

    public String removeRoom(int roomID) {
        String msg = "";

        if(roomRepository.findById(roomID).isPresent()) {
            roomRepository.deleteById(roomID);

            msg = "Item " + roomID + " is successfully deleted!";
        }

        return msg;
    }
}
