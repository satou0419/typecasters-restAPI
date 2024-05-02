package com.typecasters.apitowerofwords.Service;

import com.typecasters.apitowerofwords.Entity.RoomEntity;
import com.typecasters.apitowerofwords.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository RR;

    public RoomEntity insertArchiveAchievement(RoomEntity achievement) {
        return RR.save(achievement);
    }

    public List<RoomEntity> viewAllArchiveAchievement() {
        return RR.findAll();
    }

    public Optional<RoomEntity> viewArchiveAchievementByID(int AAID) {
        return RR.findById(AAID);
    }

    public RoomEntity editArchiveAchievement(RoomEntity achievement) {
        RoomEntity edit = new RoomEntity();

        try {
            edit = RR.findById(achievement.getRoomID()).get();

            edit.setRoomName(achievement.getRoomName());

        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException ("Achievement " + achievement.getRoomID() + " does not exist");
        }finally {
            return RR.save(edit);
        }
    }

    public RoomEntity removeArchiveAchievement(int AAID) {
        RoomEntity delete = new RoomEntity();

        try {
            delete = RR.findById(AAID).get();

            delete.setDeleted(true);
        }catch(NoSuchElementException ex) {
            throw new NoSuchElementException("Achievement " + AAID + " does not exist!");
        }finally {
            return RR.save(delete);
        }
    }
}
