package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_simulation_participants")
public class SimulationParticipantsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationParticipantsID;

    @Column(unique = true)
    private int userID;
    private int score;
    private int time;
    private boolean isDone;

    public SimulationParticipantsEntity() {
    }

    public SimulationParticipantsEntity(int simulationParticipantsID, int userID, int score, int time, boolean isDone) {
        this.simulationParticipantsID = simulationParticipantsID;
        this.userID = userID;
        this.score = score;
        this.time = time;
        this.isDone = isDone;
    }

    public int getSimulationParticipantsID() {
        return simulationParticipantsID;
    }

    public void setSimulationParticipantsID(int simulationParticipantsID) {
        this.simulationParticipantsID = simulationParticipantsID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
