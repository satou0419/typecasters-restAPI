package com.typecasters.apitowerofwords.Entity;

import com.typecasters.apitowerofwords.FieldConverter;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String username;

    @Convert(converter = FieldConverter.class)
    private String password;

    private String userType;
    private String email;


    public UserEntity() {
        super();
    }

    public UserEntity(String lastname, String firstname, String username, String password, String userType, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.email = email;
    }

    public UserEntity(int userID, String firstname, String lastname, String username, String password, String userType, String email) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
