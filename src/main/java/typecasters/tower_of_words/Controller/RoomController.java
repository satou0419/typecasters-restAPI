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
    public ResponseEntity<RoomEntity> insertRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity insertRoom = roomService.insertRoom(room);
            return new ResponseEntity<>(insertRoom, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/insert_member/{userID}/room/{roomID}")
    public ResponseEntity<String> insertMember(@PathVariable int userID, @PathVariable int roomID) {
        try {
            roomService.insertMember(userID, roomID);
            return ResponseEntity.ok("Student Added!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/insert_member_by_code/{userID}/code/{code}")
    public ResponseEntity<String> insertMemberByCode(@PathVariable int userID, @PathVariable String code) {
        try {
            roomService.insertMemberByCode(userID, code);
            RoomEntity room = roomRepository.findByCode(code);
            return ResponseEntity.ok("Welcome to " + room.getName() + "!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    //READ
    @GetMapping("/view_created_room/{creatorID}")
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

    @GetMapping("/view_room_by_code/{code}")
    public ResponseEntity<RoomEntity> viewRoomByCode(@PathVariable String code) {
        try{
            RoomEntity room = roomService.viewRoomByCode(code);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view_room_by_id/{roomID}")
    public ResponseEntity<RoomEntity> viewRoomByID(@PathVariable int roomID) {
        try{
            Optional<RoomEntity> roomOptional = roomService.viewRoomByID(roomID);
            return roomOptional.map(room -> new ResponseEntity<>(room, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view_room_by_member/{userID}")
    public ResponseEntity<List<RoomEntity>> viewRoomByMember(@PathVariable int userID) {
        try{
            List<RoomEntity> memberRooms = roomService.viewRoomByMember(userID);
            return new ResponseEntity<>(memberRooms, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException | NullPointerException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE
    @PutMapping("/edit")
    public ResponseEntity<RoomEntity> editRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity updatedRoom = roomService.editRoom(room);
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
