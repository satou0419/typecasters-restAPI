package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_simulations")
public class SimulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationID;

    private int roomID;
    private int simulationType;
    private String name;
    private String deadline;
    private int duration;
    private boolean items;
    private boolean description;
    private boolean pronunciation;
    private boolean allowReply;
    private boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tbl_simulation_words")
    private List<SimulationWordEntity> words = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_simulation_participants",
            joinColumns = @JoinColumn(name = "simulationID"),
            inverseJoinColumns = @JoinColumn(name = "userID")
    )
    private List<UserEntity> participants = new ArrayList<>();

    public SimulationEntity() {
    }

    public SimulationEntity(int simulationID, int roomID, int simulationType, String name, String deadline, int duration, boolean items, boolean description, boolean pronunciation, boolean allowReply, List<UserEntity> participants, boolean isDeleted, List<SimulationWordEntity> words) {
        this.simulationID = simulationID;
        this.roomID = roomID;
        this.simulationType = simulationType;
        this.name = name;
        this.deadline = deadline;
        this.duration = duration;
        this.items = items;
        this.description = description;
        this.pronunciation = pronunciation;
        this.allowReply = allowReply;
        this.participants = participants;
        this.isDeleted = isDeleted;
        this.words = words;
    }

    public int getSimulationID() {
        return simulationID;
    }

    public void setSimulationID(int simulationID) {
        this.simulationID = simulationID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getSimulationType() {
        return simulationType;
    }

    public void setSimulationType(int simulationType) {
        this.simulationType = simulationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isItems() {
        return items;
    }

    public void setItems(boolean items) {
        this.items = items;
    }

    public boolean isDescription() {
        return description;
    }

    public void setDescription(boolean description) {
        this.description = description;
    }

    public boolean isPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(boolean pronunciation) {
        this.pronunciation = pronunciation;
    }

    public boolean isAllowReply() {
        return allowReply;
    }

    public void setAllowReply(boolean allowReply) {
        this.allowReply = allowReply;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<SimulationWordEntity> getWords() {
        return words;
    }

    public void setWords(List<SimulationWordEntity> words) {
        this.words = words;
    }

    public void addWord(SimulationWordEntity word) {
        this.words.add(word);
    }
}
