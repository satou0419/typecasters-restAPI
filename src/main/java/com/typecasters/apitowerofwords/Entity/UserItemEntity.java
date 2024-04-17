package com.typecasters.apitowerofwords.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_item")
public class UserItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_item_id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetailsEntity user_details;

    public UserItemEntity(int user_item_id, int quantity, ItemEntity item, UserDetailsEntity user_details) {
        super();
        this.user_item_id = user_item_id;
        this.quantity = quantity;
        this.item = item;
        this.user_details = user_details;
    }

    public UserItemEntity() {
        super();
    }

    public int getUser_item_id() {
        return user_item_id;
    }

    public void setUser_item_id(int user_item_id) {
        this.user_item_id = user_item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public UserDetailsEntity getUser_details() {
        return user_details;
    }

    public void setUser_details(UserDetailsEntity user_details) {
        this.user_details = user_details;
    }
}
