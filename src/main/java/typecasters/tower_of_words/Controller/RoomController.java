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
    @PostMapping("/create_room")
    public ResponseEntity<Object> createRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity createdRoom = roomService.createRoom(room);
            return Response.response(HttpStatus.OK, "Room created successfully", createdRoom);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/{roomID}/insert_student/{userID}")
    public ResponseEntity<Object> insertStudent(@PathVariable int userID, @PathVariable int roomID) {
        try {
            roomService.insertStudent(userID, roomID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Student added successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/{code}/join_room/{userID}")
    public ResponseEntity<Object> joinRoom(@PathVariable int userID, @PathVariable String code) {
        try {
            roomService.joinRoom(userID, code);
            RoomEntity room = roomRepository.findByCode(code);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Welcome to " + room.getName() + "!");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //READ
    @GetMapping("/view_created_rooms/{creatorID}")
    public ResponseEntity<Object> viewCreatedRooms(@PathVariable int creatorID) {
        try {
            List<RoomEntity> createdRooms = roomService.viewCreatedRooms(creatorID);
            if (createdRooms.isEmpty()) {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No rooms found for the given creator ID");
            }
            return Response.response(HttpStatus.OK, "Rooms retrieved successfully", createdRooms);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/room_details_by_code/{code}")
    public ResponseEntity<Object> roomsDetailsByCode(@PathVariable String code) {
        try {
            RoomEntity room = roomService.roomsDetailsByCode(code);
            return Response.response(HttpStatus.OK, "Room details retrieved successfully", room);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/room_details_by_id/{roomID}")
    public ResponseEntity<Object> roomsDetailsByID(@PathVariable int roomID) {
        try {
            Optional<RoomEntity> roomOptional = roomService.roomsDetailsByID(roomID);
            return roomOptional.map(room -> Response.response(HttpStatus.OK, "Room details retrieved successfully", room))
                    .orElseGet(() -> NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Room not found"));
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/student_rooms/{userID}")
    public ResponseEntity<Object> studentRooms(@PathVariable int userID) {
        try {
            List<RoomEntity> memberRooms = roomService.studentRooms(userID);
            if (memberRooms.isEmpty()) {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No rooms found for the given user ID");
            }
            return Response.response(HttpStatus.OK, "Rooms retrieved successfully", memberRooms);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/students/{roomID}")
    public ResponseEntity<Object> roomStudents(@PathVariable int roomId) {
        try {
            List<Integer> memberIds = roomService.roomStudents(roomId);
            if (memberIds.isEmpty()) {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "No students found for the given room ID");
            }
            return Response.response(HttpStatus.OK, "Students retrieved successfully", memberIds);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    //UPDATE
    @PutMapping("/rename")
    public ResponseEntity<Object> editRoom(@RequestBody RoomEntity room) {
        try {
            RoomEntity updatedRoom = roomService.renameRoom(room);
            return Response.response(HttpStatus.OK, "Room renamed successfully", updatedRoom);
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //DELETE
    @PutMapping("/remove/{roomID}")
    public ResponseEntity<Object> removeRoom(@PathVariable int roomID) {
        try {
            roomService.removeRoom(roomID);
            return NoDataResponse.noDataResponse(HttpStatus.OK, "Room removed successfully");
        } catch (IllegalArgumentException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (NoSuchElementException | NullPointerException ex) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
