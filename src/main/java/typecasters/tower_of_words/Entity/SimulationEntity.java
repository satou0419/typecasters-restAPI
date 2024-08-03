package typecasters.tower_of_words.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_simulation")
public class SimulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int simulationID;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "roomID")
    private RoomEntity roomID;

    private String simulationType;

    private String name;

    private LocalDateTime deadline;

    private int attackInterval;

    private int studentLife;

//    private int numberOfAttempt;

    private boolean items;

    private boolean description;

    private boolean pronunciation;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_simulation_enemy",
            joinColumns = @JoinColumn(name = "simulationID"),
            inverseJoinColumns = @JoinColumn(name = "simulationEnemyID")
    )
    private List<SimulationEnemyEntity> enemy = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_simulation_users",
            joinColumns = @JoinColumn(name = "simulationID"),
            inverseJoinColumns = @JoinColumn(name = "simulationParticipantsID")
    )
    private List<SimulationParticipantsEntity> participants = new ArrayList<>();

    @OneToMany(mappedBy = "simulationID", cascade = CascadeType.ALL)
    private List<SimulationWordAssessmentEntity> assessment = new ArrayList<>();

    public void addWord(SimulationEnemyEntity enemy) {
        this.enemy.add(enemy);
    }

    public void addParticipants(SimulationParticipantsEntity participants){
        this.participants.add(participants);
    }
}
