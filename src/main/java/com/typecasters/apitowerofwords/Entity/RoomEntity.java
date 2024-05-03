package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;

    private int creatorID;
    private String roomName;
    private String code;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_room_members",
            joinColumns = @JoinColumn(name = "roomID"),
            inverseJoinColumns = @JoinColumn(name = "userID")
    )
    private List<UserEntity> members = new ArrayList<>();
    private boolean isDeleted = false;

    public RoomEntity() {
    }

    public RoomEntity(int roomID, int creatorID, String roomName, String code, List<UserEntity> members, boolean isDeleted) {
        this.roomID = roomID;
        this.creatorID = creatorID;
        this.roomName = roomName;
        this.code = code;
        this.members = members;
        this.isDeleted = isDeleted;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void addMembers(UserEntity user) {
        this.members.add(user);
    }
}
