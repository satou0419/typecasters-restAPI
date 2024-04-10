package com.typecasters.apitowerofwords.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentEntity {

    @Id
    private Long id;
    private int sid;
    private String fname;
    private String lname;
    private String gender;

    public StudentEntity(String gender, String lname, String fname, int sid, Long id) {
        this.gender = gender;
        this.lname = lname;
        this.fname = fname;
        this.sid = sid;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSid() {
        return sid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public StudentEntity() {
    }
}
