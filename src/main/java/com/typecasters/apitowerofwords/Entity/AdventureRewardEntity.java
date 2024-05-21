package com.typecasters.apitowerofwords.Entity;

import com.typecasters.apitowerofwords.RewardItem;

import javax.persistence.*;

@Entity
@Table(name = "tbl_adventure_reward")
public class AdventureRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adventure_reward_id")
    private int advRewardId;

    private int advRewardCredit;

    private int towerFloorId;

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

    public AdventureRewardEntity() {}

    public AdventureRewardEntity(RewardItem rewardItemOne, RewardItem rewardItemTwo, int towerFloorId, int advRewardCredit, int advRewardId) {
        this.rewardItemOne = rewardItemOne;
        this.rewardItemTwo = rewardItemTwo;
        this.towerFloorId = towerFloorId;
        this.advRewardCredit = advRewardCredit;
        this.advRewardId = advRewardId;
    }

    public AdventureRewardEntity(int advRewardCredit, int towerFloorId, RewardItem rewardItemOne, RewardItem rewardItemTwo) {
        this.advRewardCredit = advRewardCredit;
        this.towerFloorId = towerFloorId;
        this.rewardItemOne = rewardItemOne;
        this.rewardItemTwo = rewardItemTwo;
    }

    public int getAdvRewardId() {
        return advRewardId;
    }

    public void setAdvRewardId(int advRewardId) {
        this.advRewardId = advRewardId;
    }

    public int getAdvRewardCredit() {
        return advRewardCredit;
    }

    public void setAdvRewardCredit(int advRewardCredit) {
        this.advRewardCredit = advRewardCredit;
    }

    public int getTowerFloorId() {
        return towerFloorId;
    }

    public void setTowerFloorId(int towerFloorId) {
        this.towerFloorId = towerFloorId;
    }

    public RewardItem getRewardItemOne() {
        return rewardItemOne;
    }

    public void setRewardItemOne(RewardItem rewardItemOne) {
        this.rewardItemOne = rewardItemOne;
    }

    public RewardItem getRewardItemTwo() {
        return rewardItemTwo;
    }

    public void setRewardItemTwo(RewardItem rewardItemTwo) {
        this.rewardItemTwo = rewardItemTwo;
    }
}
