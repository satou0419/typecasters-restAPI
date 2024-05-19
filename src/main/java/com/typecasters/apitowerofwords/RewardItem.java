package com.typecasters.apitowerofwords;

import javax.persistence.Embeddable;

@Embeddable
public class RewardItem {
    int rewardItemId;
    int rewardItemQuantity;


    public int getRewardItemId() {
        return rewardItemId;
    }

    public void setRewardItemId(int rewardItemId) {
        this.rewardItemId = rewardItemId;
    }

    public int getRewardItemQuantity() {
        return rewardItemQuantity;
    }

    public void setRewardItemQuantity(int rewardItemQuantity) {
        this.rewardItemQuantity = rewardItemQuantity;
    }

    public RewardItem() {
    }

    public RewardItem(int rewardItemId, int rewardItemQuantity) {
        this.rewardItemId = rewardItemId;
        this.rewardItemQuantity = rewardItemQuantity;
    }
}
