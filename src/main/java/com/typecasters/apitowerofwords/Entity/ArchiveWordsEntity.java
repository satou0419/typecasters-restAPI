package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_archive_words")
public class ArchiveWordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AWID;

    private int UID;
    private String word;
    @Column(name = "check_column")
    private boolean check;
    private boolean isDeleted = false;

    public ArchiveWordsEntity() {
    }

    public ArchiveWordsEntity(int AWID, int UID, String word, boolean check, boolean isDeleted) {
        this.AWID = AWID;
        this.UID = UID;
        this.word = word;
        this.check = check;
        this.isDeleted = isDeleted;
    }

    public int getAWID() {
        return AWID;
    }

    public void setAWID(int AWID) {
        this.AWID = AWID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
