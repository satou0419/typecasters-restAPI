package typecasters.tower_of_words.Controller;

import typecasters.tower_of_words.Entity.RoomEntity;
import typecasters.tower_of_words.Repository.RoomRepository;
import typecasters.tower_of_words.Response.NoDataResponse;
import typecasters.tower_of_words.Response.Response;
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
    @PostMapping("/insert")
    public ResponseEntity<Object> insertRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity insertedRoom = roomService.insertRoom(room);
            return Response.response(HttpStatus.OK, "Room inserted successfully", insertedRoom);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/insert_member/{userID}/room/{roomID}")
    public ResponseEntity<Object> insertMember(@PathVariable int userID, @PathVariable int roomID) {
        try {
            roomService.insertMember(userID, roomID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Student Added!");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/insert_member_by_code/{userID}/code/{code}")
    public ResponseEntity<Object> insertMemberByCode(@PathVariable int userID, @PathVariable String code) {
        try {
            roomService.insertMemberByCode(userID, code);
            RoomEntity room = roomRepository.findByCode(code);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Welcome to " + room.getName() + "!");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //READ
    @GetMapping("/view_created_room/{creatorID}")
    public ResponseEntity<Object> viewCreatedRooms(@PathVariable int creatorID) {
        try {
            List<RoomEntity> createdRooms = roomService.viewCreatedRooms(creatorID);
            return Response.response(HttpStatus.OK, "Created rooms retrieved successfully", createdRooms);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/view_room_by_code/{code}")
    public ResponseEntity<Object> viewRoomByCode(@PathVariable String code) {
        try {
            RoomEntity room = roomService.viewRoomByCode(code);
            return Response.response(HttpStatus.OK, "Room retrieved successfully", room);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/view_room_by_id/{roomID}")
    public ResponseEntity<Object> viewRoomByID(@PathVariable int roomID) {
        try {
            Optional<RoomEntity> roomOptional = roomService.viewRoomByID(roomID);
            return roomOptional.map(room -> Response.response(HttpStatus.OK, "Room retrieved successfully", room))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Room not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/view_room_by_member/{userID}")
    public ResponseEntity<Object> viewRoomByMember(@PathVariable int userID) {
        try {
            List<RoomEntity> memberRooms = roomService.viewRoomByMember(userID);
            return Response.response(HttpStatus.OK, "Member rooms retrieved successfully", memberRooms);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //UPDATE
    @PutMapping("/edit")
    public ResponseEntity<Object> editRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity updatedRoom = roomService.editRoom(room);
            return Response.response(HttpStatus.OK, "Room updated successfully", updatedRoom);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //DELETE
    @PutMapping("/remove/{roomID}")
    public ResponseEntity<Object> removeRoom(@PathVariable int roomID) {
        try {
            roomService.removeRoom(roomID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Room Removed!");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex){
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
