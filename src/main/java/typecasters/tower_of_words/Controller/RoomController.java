package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.RoomEntity;
import typecasters.tower_of_words.Repository.RoomRepository;
import typecasters.tower_of_words.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    //CREATE
    @PostMapping("/create_room")
    public ResponseEntity<RoomEntity> createRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity createdRoom = roomService.createRoom(room);
            return new ResponseEntity<>(createdRoom, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{roomID}/insert_student/{userID}")
    public ResponseEntity<String> insertStudent(@PathVariable int userID, @PathVariable int roomID) {
        try {
            roomService.insertStudent(userID, roomID);
            return ResponseEntity.ok("Student Added!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{code}/join_room/{userID}")
    public ResponseEntity<String> joinRoom(@PathVariable int userID, @PathVariable String code) {
        try {
            roomService.joinRoom(userID, code);
            RoomEntity room = roomRepository.findByCode(code);
            return ResponseEntity.ok("Welcome to " + room.getName() + "!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    //READ
    @GetMapping("/view_created_rooms/{creatorID}")
    public ResponseEntity<List<RoomEntity>> viewCreatedRooms(@PathVariable int creatorID) {
        try{
            List<RoomEntity> createdRooms = roomService.viewCreatedRooms(creatorID);
            return new ResponseEntity<>(createdRooms, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/room_details_by_code/{code}")
    public ResponseEntity<RoomEntity> roomsDetailsByCode(@PathVariable String code) {
        try{
            RoomEntity room = roomService.roomsDetailsByCode(code);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/room_details_by_id/{roomID}")
    public ResponseEntity<RoomEntity> roomsDetailsByID(@PathVariable int roomID) {
        try{
            Optional<RoomEntity> roomOptional = roomService.roomsDetailsByID(roomID);
            return roomOptional.map(room -> new ResponseEntity<>(room, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student_rooms/{userID}")
    public ResponseEntity<List<RoomEntity>> studentRooms(@PathVariable int userID) {
        try{
            List<RoomEntity> memberRooms = roomService.studentRooms(userID);
            return new ResponseEntity<>(memberRooms, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students/{roomID}")
    public ResponseEntity<List<Integer>> roomStudents(@PathVariable int roomId) {
        List<Integer> memberIds = roomService.roomStudents(roomId);
        return ResponseEntity.ok(memberIds);
    }

    //UPDATE
    @PutMapping("/rename")
    public ResponseEntity<RoomEntity> editRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity updatedRoom = roomService.renameRoom(room);
            return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE
    @PutMapping("/remove/{roomID}")
    public ResponseEntity<String> removeRoom(@PathVariable int roomID) {
        try {
            roomService.removeRoom(roomID);
            return ResponseEntity.ok("Simulation Removed!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
