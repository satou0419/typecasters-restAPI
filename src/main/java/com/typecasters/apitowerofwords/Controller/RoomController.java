package com.typecasters.apitowerofwords.Controller;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import com.typecasters.apitowerofwords.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RoomEntity insertRoom(@RequestBody RoomEntity room) {
        return roomService.insertRoom(room);
    }

    @PutMapping("/insert_member/{userID}")
    public RoomEntity insertMember(@PathVariable int userID, @RequestBody RoomEntity room) {
        return roomService.insertMember(userID, room);
    }

    @PutMapping("/insert_member_by_code/{userID}/code/{code}")
    public RoomEntity insertMemberByCode(@PathVariable int userID, @PathVariable String code) {
        return roomService.insertMemberByCode(userID, code);
    }

    //READ
    @GetMapping("/view_created_room/{creatorID}")
    public List<RoomEntity> viewCreatedRooms(@PathVariable int creatorID) {
        return roomService.viewCreatedRooms(creatorID);
    }

    @GetMapping("/view_room_by_code/{code}")
    public RoomEntity viewRoomByCode(@PathVariable String code) {
        return roomService.viewRoomByCode(code);
    }

    @GetMapping("/view_room_by_id/{roomID}")
    public Optional<RoomEntity> viewRoomByID(@PathVariable int roomID) {
        return roomService.viewRoomByID(roomID);
    }

    @GetMapping("/view_room_by_member/{userID}")
    public List<RoomEntity> viewRoomByMember(@PathVariable int userID) {
        return roomService.viewRoomByMember(userID);
    }

    //UPDATE
    @PutMapping("/edit")
    public RoomEntity editRoom(@RequestBody RoomEntity room) {
        return roomService.editRoom(room);
    }

    //DELETE
    @PutMapping("/delete/{roomID}")
    public RoomEntity removeRoom(@PathVariable int roomID) {
        return roomService.removeRoom(roomID);
    }
}
