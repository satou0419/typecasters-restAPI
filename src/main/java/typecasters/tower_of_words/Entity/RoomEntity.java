package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;

    private int creatorID;

    private String name;

    @Column(unique = true)
    private String code;

    @ElementCollection
    @CollectionTable(name = "tbl_room_members", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "user_id")
    private List<Integer> members = new ArrayList<>();

    private boolean isDeleted = false;

    public void addMembers(Integer user) {
        this.members.add(user);
    }
}
