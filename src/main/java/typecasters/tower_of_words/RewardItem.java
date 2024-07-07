package typecasters.tower_of_words;

import typecasters.tower_of_words.Entity.ItemEntity;

import jakarta.persistence.*;

@Embeddable
public class RewardItem {

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "reward_item_id", insertable = false, updatable = false)
    private ItemEntity rewardItem;

    private int rewardItemQuantity;

    public RewardItem() {}

    public RewardItem(ItemEntity rewardItem, int rewardItemQuantity) {
        this.rewardItem = rewardItem;
        this.rewardItemQuantity = rewardItemQuantity;
    }

    public ItemEntity getRewardItem() {
        return rewardItem;
    }

    public void setRewardItem(ItemEntity rewardItem) {
        this.rewardItem = rewardItem;
    }

    public int getRewardItemQuantity() {
        return rewardItemQuantity;
    }

    public void setRewardItemQuantity(int rewardItemQuantity) {
        this.rewardItemQuantity = rewardItemQuantity;
    }
}
