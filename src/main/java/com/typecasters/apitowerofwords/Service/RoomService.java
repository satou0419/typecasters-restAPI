package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import com.typecasters.apitowerofwords.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public RoomEntity insertRoom(RoomEntity room) {
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

    public RoomEntity insertMember(Integer userID, RoomEntity room) {
        room.addMembers(userID);
        return roomRepository.save(room);
    }

    public RoomEntity insertMemberByCode(Integer userID, String code) {
        RoomEntity room = new RoomEntity();

        try {
            room = roomRepository.findByCode(code);
            room.addMembers(userID);

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + userID + " does not exist");
        }finally {
            return roomRepository.save(room);
        }
    }

    public List<RoomEntity> viewCreatedRooms(int creatorID) {
        return roomRepository.findByCreatorID(creatorID);
    }

    public RoomEntity viewRoomByCode(String code) {
        return roomRepository.findByCode(code);
    }

    public Optional<RoomEntity> viewRoomByID(int roomID) {
        return roomRepository.findById(roomID);
    }

    public List<RoomEntity> viewRoomByMember(Integer userID) {
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

    public RoomEntity editRoom(RoomEntity room) {
        RoomEntity edit = new RoomEntity();

        try {
            edit = roomRepository.findById(room.getRoomID()).get();

            edit.setRoomName(room.getRoomName());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Room " + room.getRoomID() + " does not exist");
        }finally {
            return roomRepository.save(edit);
        }
    }

    public RoomEntity removeRoom(int roomID) {
        RoomEntity delete = new RoomEntity();

        try {
            delete = roomRepository.findById(roomID).get();

            delete.setDeleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Room " + roomID + " does not exist!");
        }finally {
            return roomRepository.save(delete);
        }
    }
}
