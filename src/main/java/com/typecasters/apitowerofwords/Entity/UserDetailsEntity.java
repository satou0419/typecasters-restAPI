package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="tbl_user_details")
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userDetailId;
    private int userId;
    private int credit_amount;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserItemEntity> userItemList;


}
