package typecasters.tower_of_words.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_item")
public class UserItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userItemID;

    private int quantity;

    private int userID;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemID;

    public UserItemEntity(int quantity, int userID, ItemEntity itemID) {
        this.quantity = quantity;
        this.userID = userID;
        this.itemID = itemID;
    }
}
