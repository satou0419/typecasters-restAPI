package com.typecasters.apitowerofwords;

import com.typecasters.apitowerofwords.Entity.ItemEntity;

import javax.persistence.*;

@Embeddable
public class RewardItem {

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
