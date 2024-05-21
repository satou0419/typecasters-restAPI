package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_item")
public class UserItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_item_id")
    private int userItemId;

    private int quantity;

    private int userId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemId;

//    @JsonBackReference
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_details_id")
//    private UserDetailsEntity userDetails;


//    public UserItemEntity(int user_item_id, int quantity, int item_id, UserDetailsEntity user_details) {
//        super();
//        this.user_item_id = user_item_id;
//        this.quantity = quantity;
//        this.item_id = item_id;
//        this.user_details = user_details;
//    }


    public UserItemEntity(int userItemId, int quantity, int userId, ItemEntity itemId) {
        this.userItemId = userItemId;
        this.quantity = quantity;
        this.userId = userId;
        this.itemId = itemId;
    }

    public UserItemEntity(int quantity, int userId, ItemEntity itemId) {
        this.quantity = quantity;
        this.userId = userId;
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserItemEntity() {
        super();
    }

    public int getUserItemId() {
        return userItemId;
    }

    public void setUserItemId(int userItemId) {
        this.userItemId = userItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemEntity getItemId() {
        return itemId;
    }

    public void setItemId(ItemEntity itemId) {
        this.itemId = itemId;
    }

//    public UserDetailsEntity getUser_details() {
//        return user_details;
//    }
//
//    public void setUser_details(UserDetailsEntity user_details) {
//        this.user_details = user_details;
//    }
}
