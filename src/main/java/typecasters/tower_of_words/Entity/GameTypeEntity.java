package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_game_entity")
public class GameTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameTypeID;

    private String gameTypeName;

    private String gameTypeDescription;
}
