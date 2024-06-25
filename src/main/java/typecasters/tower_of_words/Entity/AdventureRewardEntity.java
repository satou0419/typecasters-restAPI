package typecasters.tower_of_words.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import typecasters.tower_of_words.RewardItem;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_adventure_reward")
public class AdventureRewardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adventureRewardID;

    private int adventureRewardCredit;

    private int towerFloorID;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rewardItemQuantity", column = @Column(name = "reward_item_one_quantity"))
    })
    @AssociationOverride(name = "rewardItem", joinColumns = @JoinColumn(name = "reward_item_one_id"))
    private RewardItem rewardItemOne;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rewardItemQuantity", column = @Column(name = "reward_item_two_quantity"))
    })
    @AssociationOverride(name = "rewardItem", joinColumns = @JoinColumn(name = "reward_item_two_id"))
    private RewardItem rewardItemTwo;
}
