package com.typecasters.apitowerofwords.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tbl_item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private int itemId;

    private String item_name;

    private String image_path;

    private String item_description;

    private int item_price;

    @JsonIgnore
    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL)
    private List<UserItemEntity> userItems;

    public ItemEntity() {
        super();
    }

    public ItemEntity(int itemId, String item_name, String image_path, String item_description, int item_price) {
        this.itemId = itemId;
        this.item_name = item_name;
        this.image_path = image_path;
        this.item_description = item_description;
        this.item_price = item_price;
    }

    public ItemEntity(int itemId, String image_path, String item_name, List<UserItemEntity> userItems) {
        this.itemId = itemId;
        this.image_path = image_path;
        this.item_name = item_name;
        this.userItems = userItems;
    }

    public List<UserItemEntity> getUserItems() {
        return userItems;
    }

    public void setUserItems(List<UserItemEntity> userItems) {
        this.userItems = userItems;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }
}
