package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="tbl_user_details")
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_detail_id")
    private int user_detail_id;
    private int user_id;
    private int credit_amount ;
    private int words_collected ;
    private int achievement_count ;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<UserItemEntity> userItemList;


    public UserDetailsEntity() {
    }

    public UserDetailsEntity(int user_id, int credit_amount, int words_collected, int achievement_count) {
        this.user_id = user_id;
        this.credit_amount = credit_amount;
        this.words_collected = words_collected;
        this.achievement_count = achievement_count;
    }

    public UserDetailsEntity(int user_detail_id, int user_id, int credit_amount, int words_collected, int achievement_count) {
        this.user_detail_id = user_detail_id;
        this.user_id = user_id;
        this.credit_amount = credit_amount;
        this.words_collected = words_collected;
        this.achievement_count = achievement_count;
    }

    public int getUser_detail_id() {
        return user_detail_id;
    }

    public void setUser_detail_id(int user_detail_id) {
        this.user_detail_id = user_detail_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(int credit_amount) {
        this.credit_amount = credit_amount;
    }

    public int getWords_collected() {
        return words_collected;
    }

    public void setWords_collected(int words_collected) {
        this.words_collected = words_collected;
    }

    public int getAchievement_count() {
        return achievement_count;
    }

    public void setAchievement_count(int achievement_count) {
        this.achievement_count = achievement_count;
    }
}
