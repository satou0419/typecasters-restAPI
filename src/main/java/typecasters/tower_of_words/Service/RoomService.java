package typecasters.tower_of_words.Service;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import typecasters.tower_of_words.Entity.*;
import typecasters.tower_of_words.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    @Autowired
//    SimulationAttemptsService simulationAttemptsService;

    @Autowired
    StudentWordProgressService studentWordProgressService;

    @Autowired
    @Lazy
    SimulationParticipantsService simulationParticipantsService;

    @Autowired
    SimulationWordsRepository simulationWordsRepository;

    @Autowired
    SimulationAssessmentRepository simulationAssessmentRepository;

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

    @Transactional
    public RoomEntity insertStudent(Integer userID, int roomID) {
        RoomEntity room = roomRepository.findById(roomID).orElseThrow(() -> new NoSuchElementException("Room " + roomID + " does not exist"));
        List<SimulationEntity> simulations = simulationRepository.findAllByRoomID(room);
        LocalDateTime now = LocalDateTime.now();

        if (room.getMembers().contains(userID)) {
            throw new IllegalArgumentException("User " + userID + " already exists in the room");
        }
        room.addMembers(userID);

        for (SimulationEntity simulation : simulations) {
            if (simulation.getDeadline() != null && now.isBefore(simulation.getDeadline())) {
                addStudentToSimulation(userID, simulation);
            }
        }

        for (SimulationEntity simulation : simulations) {
            if (simulation.getDeadline() != null && now.isBefore(simulation.getDeadline())) {
                SimulationAssessmentEntity assessment = simulationAssessmentRepository.findOneBySimulationID(simulation.getSimulationID())
                        .orElse(new SimulationAssessmentEntity(
                                simulation.getSimulationID(),
                                0,  // totalParticipants
                                0,  // numberOfParticipants
                                0,  // missedTakers
                                0.0, // simulationAccuracyRate
                                0.0  // simulationDurationAverage
                        ));
                assessment.setTotalParticipants(assessment.getTotalParticipants() + 1);
                simulationAssessmentRepository.save(assessment);
            }
        }


        return roomRepository.save(room);
    }

    public Optional<RoomEntity> findRoomBySimulationIDAndUserID(int simulationID, int userID) {
        return roomRepository.findOneBySimulationIDAndUserID(simulationID, userID);
    }

    @Transactional
    public RoomEntity joinRoom(Integer userID, String code) {
        RoomEntity room = roomRepository.findByCode(code);

        if (room == null) {
            throw new NoSuchElementException("Room with code " + code + " does not exist");
        }

        List<SimulationEntity> simulations = simulationRepository.findAllByRoomID(room);
        LocalDateTime now = LocalDateTime.now();

        if (room.getMembers().contains(userID)) {
            throw new IllegalArgumentException("User " + userID + " already exists in the room");
        }
        room.addMembers(userID);

        for (SimulationEntity simulation : simulations) {
            if (simulation.getDeadline() != null && now.isBefore(simulation.getDeadline())) {
                addStudentToSimulation(userID, simulation);
            }
        }

        for (SimulationEntity simulation : simulations) {
            if (simulation.getDeadline() != null && now.isBefore(simulation.getDeadline())) {
                SimulationAssessmentEntity assessment = simulationAssessmentRepository.findOneBySimulationID(simulation.getSimulationID())
                        .orElse(new SimulationAssessmentEntity(
                                simulation.getSimulationID(),
                                0,  // totalParticipants
                                0,  // numberOfParticipants
                                0,  // missedTakers
                                0.0, // simulationAccuracyRate
                                0.0  // simulationDurationAverage
                        ));
                assessment.setTotalParticipants(assessment.getTotalParticipants() + 1);
                simulationAssessmentRepository.save(assessment);
            }
        }

        return roomRepository.save(room);
    }

    private void addStudentToSimulation(Integer userID, SimulationEntity simulation)

    {

        SimulationParticipantsEntity participant = new SimulationParticipantsEntity(
                userID, // userID
                0, // mistakes
                0, // score
                0, // duration
                0, // accuracy
                false, // isDone
                simulation
        );

        participant = simulationParticipantsService.addParticipant(participant); // Ensure participant is saved before use
        simulation.addParticipants(participant);
        simulationRepository.save(simulation);

        List<SimulationWordAssessmentEntity> assessments = simulationWordAssessmentService.getAllBySimulationID(simulation.getSimulationID());

        // Generate StudentWordProgress
        List<StudentWordProgressEntity> progressList = new ArrayList<>();
        for (SimulationWordAssessmentEntity assessment : assessments) {
            progressList.add(new StudentWordProgressEntity(
                    assessment.getSimulationWordID(),
                    userID, // studentID
                    simulation.getSimulationID(),
                    0, // Mistake
                    false, // Correct
                    0, // Score
                    0, // Duration
                    0  // Accuracy
            ));
        }
        studentWordProgressService.addProgress(progressList);
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
        RoomEntity edit = roomRepository.findById(room.getRoomID())
                        .orElseThrow(() -> new NoSuchElementException("Room doesn't exist!"));

        edit.setName(room.getName());

        return roomRepository.save(edit);

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
