package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_progress")
public class TowerProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userProgId;

    private int userDetailsId;
    private int towerSecProg;
    private int floorId;

    @OneToOne(mappedBy = "userProgress")
    private UserDetailsEntity userDetails;

    public TowerProgressEntity() {
    }

    public TowerProgressEntity(int userProgId, int userDetailsId, int towerSecProg, int floorId) {
        this.userProgId = userProgId;
        this.userDetailsId = userDetailsId;
        this.towerSecProg = towerSecProg;
        this.floorId = floorId;
    }

    public TowerProgressEntity(int userDetailsId, int towerSecProg, int floorId) {
        this.userDetailsId = userDetailsId;
        this.towerSecProg = towerSecProg;
        this.floorId = floorId;
    }

    public TowerProgressEntity(int userDetailsId, int towerSecProg, int floorId, UserDetailsEntity userDetails) {
        this.userDetailsId = userDetailsId;
        this.towerSecProg = towerSecProg;
        this.floorId = floorId;
        this.userDetails = userDetails;
    }

    public int getUserProgId() {
        return userProgId;
    }

    public void setUserProgId(int userProgId) {
        this.userProgId = userProgId;
    }

    public int getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(int userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public int getTowerSecProg() {
        return towerSecProg;
    }

    public void setTowerSecProg(int towerSecProg) {
        this.towerSecProg = towerSecProg;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
