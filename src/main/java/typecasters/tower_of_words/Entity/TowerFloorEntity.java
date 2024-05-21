package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_tower_floor")
public class TowerFloorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tower_floor_id")
    private int towerFloorId;

    private int towerSection;
    private int sectionFloor;

    public TowerFloorEntity() {
    }

    public TowerFloorEntity(int towerFloorId, int towerSection, int sectionFloor) {
        this.towerFloorId = towerFloorId;
        this.towerSection = towerSection;
        this.sectionFloor = sectionFloor;
    }

    public int getTowerFloorId() {
        return towerFloorId;
    }

    public void setTowerFloorId(int towerFloorId) {
        this.towerFloorId = towerFloorId;
    }

    public int getTowerSection() {
        return towerSection;
    }

    public void setTowerSection(int towerSection) {
        this.towerSection = towerSection;
    }

    public int getSectionFloor() {
        return sectionFloor;
    }

    public void setSectionFloor(int sectionFloor) {
        this.sectionFloor = sectionFloor;
    }
}
