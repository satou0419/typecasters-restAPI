package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import com.typecasters.apitowerofwords.Repository.RoomRepository;
import com.typecasters.apitowerofwords.Service.RoomService;
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
    @PostMapping("/insert")
    public ResponseEntity<String> insertRoom(@RequestBody RoomEntity room) {
        try {
            roomService.insertRoom(room);
            return ResponseEntity.ok("Room Created!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/insert_member/{userID}/room/{roomID}")
    public ResponseEntity<String> insertMember(@PathVariable int userID, @PathVariable int roomID) {
        try {
            roomService.insertMember(userID, roomID);
            return ResponseEntity.ok("Student Added!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/insert_member_by_code/{userID}/code/{code}")
    public ResponseEntity<String> insertMemberByCode(@PathVariable int userID, @PathVariable String code) {
        try {
            roomService.insertMemberByCode(userID, code);
            RoomEntity room = roomRepository.findByCode(code);
            return ResponseEntity.ok("Welcome to " + room.getRoomName() + "!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }

    //READ
    @GetMapping("/view_created_room/{creatorID}")
    public ResponseEntity<List<RoomEntity>> viewCreatedRooms(@PathVariable int creatorID) {
        List<RoomEntity> createdRooms = roomService.viewCreatedRooms(creatorID);
        return new ResponseEntity<>(createdRooms, HttpStatus.OK);
    }


    @GetMapping("/view_room_by_code/{code}")
    public ResponseEntity<RoomEntity> viewRoomByCode(@PathVariable String code) {
        RoomEntity room = roomService.viewRoomByCode(code);
        if (room != null) {
            return new ResponseEntity<>(room, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view_room_by_id/{roomID}")
    public ResponseEntity<RoomEntity> viewRoomByID(@PathVariable int roomID) {
        Optional<RoomEntity> roomOptional = roomService.viewRoomByID(roomID);
        return roomOptional.map(room -> new ResponseEntity<>(room, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/view_room_by_member/{userID}")
    public ResponseEntity<List<RoomEntity>> viewRoomByMember(@PathVariable int userID) {
        List<RoomEntity> memberRooms = roomService.viewRoomByMember(userID);
        return new ResponseEntity<>(memberRooms, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/edit")
    public ResponseEntity<String> editRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity updatedRoom = roomService.editRoom(room);
            return ResponseEntity.ok("Room Updated!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }


    //DELETE
    @PutMapping("/remove/{roomID}")
    public ResponseEntity<String> removeRoom(@PathVariable int roomID) {
        try {
            RoomEntity removedRoom = roomService.removeRoom(roomID);
            return ResponseEntity.ok("Room Removed!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
