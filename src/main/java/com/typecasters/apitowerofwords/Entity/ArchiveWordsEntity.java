package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_archive_words")
public class ArchiveWordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int archiveWordsID;

    private int userID;
    @Column(unique = true)
    private String word;
    @Column(name = "check_column")
    private boolean check = false;
    private boolean isDeleted = false;

    public ArchiveWordsEntity() {
    }

    public ArchiveWordsEntity(int archiveWordsID, int userID, String word, boolean check, boolean isDeleted) {
        this.archiveWordsID = archiveWordsID;
        this.userID = userID;
        this.word = word;
        this.check = check;
        this.isDeleted = isDeleted;
    }

    public int getArchiveWordsID() {
        return archiveWordsID;
    }

    public void setArchiveWordsID(int archiveWordsID) {
        this.archiveWordsID = archiveWordsID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
