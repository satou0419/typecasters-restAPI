package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "tbl_rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int RID;

    private int CID;
    private String roomName;
    private String code;
    private List<UserEntity> members = new ArrayList<UserEntity>();
    private boolean isDeleted = false;

    public RoomEntity() {
    }

    public RoomEntity(int RID, int CID, String roomName, String code, List<UserEntity> members, boolean isDeleted) {
        this.RID = RID;
        this.CID = CID;
        this.roomName = roomName;
        this.code = code;
        this.members = members;
        this.isDeleted = isDeleted;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
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
}
