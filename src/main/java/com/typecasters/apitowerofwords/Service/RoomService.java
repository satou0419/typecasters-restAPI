package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import com.typecasters.apitowerofwords.Entity.UserEntity;
import com.typecasters.apitowerofwords.Repository.RoomRepository;
import com.typecasters.apitowerofwords.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;

    public RoomEntity insertRoom(RoomEntity room) {
        return roomRepository.save(room);
    }

    public RoomEntity insertMember(int userID, RoomEntity room) {
        UserEntity user = new UserEntity();

        try {
            user = userRepository.findById(userID).get();

            room.addMembers(user);

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + userID + " does not exist");
        }finally {
            return roomRepository.save(room);
        }
    }

    public RoomEntity insertMemberByCode(int userID, String code) {
        UserEntity user = new UserEntity();
        RoomEntity room = new RoomEntity();

        try {
            user = userRepository.findById(userID).get();

            room = roomRepository.findByCode(code);
            room.addMembers(user);

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

    public List<RoomEntity> viewRoomByMember(int userID) {
        List<RoomEntity> roomList = new ArrayList<>();
        try {
            Optional<UserEntity> user = userRepository.findById(userID);
            List<RoomEntity> room = roomRepository.findAll();



            for(RoomEntity i : room) {
                for(UserEntity j : i.getMembers()) {
                    if(j.equals(user)) {
                        roomList.add(i);
                    }
                }
            }

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Achievement " /*+ achievement.getArchiveAchievementID()*/ + " does not exist");
        }
        return roomList;
    }

    public RoomEntity editRoom(RoomEntity room) {
        RoomEntity edit = new RoomEntity();

        try {
            edit = roomRepository.findById(room.getRoomID()).get();

            edit.setRoomName(room.getRoomName());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + room.getRoomID() + " does not exist");
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
            throw new NoSuchElementException("Achievement " + roomID + " does not exist!");
        }finally {
            return roomRepository.save(delete);
        }
    }
}
