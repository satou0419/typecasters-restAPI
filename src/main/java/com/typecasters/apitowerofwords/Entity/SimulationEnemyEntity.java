package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_simulation_enemy")
public class SimulationEnemyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationEnemyID;
    private String imagePath;
    private String word;

    public SimulationEnemyEntity() {
    }

    public SimulationEnemyEntity(int simulationEnemyID, int simulationID, String imagePath, String word) {
        this.simulationEnemyID = simulationEnemyID;
        this.imagePath = imagePath;
        this.word = word;
    }

    public int getSimulationEnemyID() {
        return simulationEnemyID;
    }

    public void setSimulationEnemyID(int simulationEnemyID) {
        this.simulationEnemyID = simulationEnemyID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
