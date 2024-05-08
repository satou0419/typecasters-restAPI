package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import com.typecasters.apitowerofwords.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    //CREATE
    @PostMapping("/insert")
    public ResponseEntity<RoomEntity> insertRoom(@RequestBody RoomEntity room) {
        RoomEntity insertedRoom = roomService.insertRoom(room);
        return new ResponseEntity<>(insertedRoom, HttpStatus.OK);
    }

    @PutMapping("/insert_member/{userID}")
    public ResponseEntity<RoomEntity> insertMember(@PathVariable int userID, @RequestBody RoomEntity room) {
        RoomEntity updatedRoom = roomService.insertMember(userID, room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @PutMapping("/insert_member_by_code/{userID}/code/{code}")
    public ResponseEntity<RoomEntity> insertMemberByCode(@PathVariable int userID, @PathVariable String code) {
        RoomEntity updatedRoom = roomService.insertMemberByCode(userID, code);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
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
    public ResponseEntity<RoomEntity> editRoom(@RequestBody RoomEntity room) {
        RoomEntity updatedRoom = roomService.editRoom(room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }


    //DELETE
    @PutMapping("/remove/{roomID}")
    public ResponseEntity<RoomEntity> removeRoom(@PathVariable int roomID) {
        RoomEntity removedRoom = roomService.removeRoom(roomID);
        return new ResponseEntity<>(removedRoom, HttpStatus.OK);
    }
}
