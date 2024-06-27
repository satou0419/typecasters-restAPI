package typecasters.tower_of_words.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemID;

    private String name;

    private String imagePath;

    private String description;

    private int price;

    @JsonIgnore
    @OneToMany(mappedBy = "itemID", cascade = CascadeType.ALL)
    private List<UserItemEntity> userItems;
}
