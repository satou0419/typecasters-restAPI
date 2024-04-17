package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name="tbl_item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    private String item_name;

    private String image_path;

    private String item_description;

    private int item_price;

    public ItemEntity() {
        super();
    }

    public ItemEntity(int item_id, String item_name, String image_path, String item_description, int item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.image_path = image_path;
        this.item_description = item_description;
        this.item_price = item_price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
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
