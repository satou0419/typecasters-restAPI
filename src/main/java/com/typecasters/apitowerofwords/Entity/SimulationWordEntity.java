package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_simulation_words")
public class SimulationWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationID;

    private String word;

    public SimulationWordEntity() {
    }

    public SimulationWordEntity(int simulationID, String word) {
        this.simulationID = simulationID;
        this.word = word;
    }

    public int getSimulationID() {
        return simulationID;
    }

    public void setSimulationID(int simulationID) {
        this.simulationID = simulationID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
