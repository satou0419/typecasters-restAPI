package typecasters.tower_of_words.Testing;

import typecasters.tower_of_words.Entity.ItemEntity;

public class ReturnReward {
    private int advRewardCredit;
    private int towerFloorId;

    private int quantity1;
    private int quantity2;

    private ItemEntity item1;
    private ItemEntity item2;

    public ReturnReward() {
    }

    public ReturnReward(int towerFloorId, int advRewardCredit, int quantity1, int quantity2, ItemEntity item1, ItemEntity item2) {
        this.towerFloorId = towerFloorId;
        this.advRewardCredit = advRewardCredit;
        this.quantity1 = quantity1;
        this.quantity2 = quantity2;
        this.item1 = item1;
        this.item2 = item2;
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

    public ItemEntity getItem1() {
        return item1;
    }

    public void setItem1(ItemEntity item1) {
        this.item1 = item1;
    }

    public int getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(int quantity2) {
        this.quantity2 = quantity2;
    }

    public int getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(int quantity1) {
        this.quantity1 = quantity1;
    }

    public ItemEntity getItem2() {
        return item2;
    }

    public void setItem2(ItemEntity item2) {
        this.item2 = item2;
    }
}
