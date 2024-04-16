package com.typecasters.apitowerofwords.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_archive_words")
public class ArchiveWordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int archive_words_id;

    private int archive_words_user_id;
    private String archive_words_word;
    private boolean archive_words_check = false;
    private boolean archive_words_is_deleted = false;

    public ArchiveWordsEntity() {
    }

    public ArchiveWordsEntity(int archive_words_id, int archive_words_user_id, String archive_words_word, boolean archive_words_check, boolean archive_words_is_deleted) {
        this.archive_words_id = archive_words_id;
        this.archive_words_user_id = archive_words_user_id;
        this.archive_words_word = archive_words_word;
        this.archive_words_check = archive_words_check;
        this.archive_words_is_deleted = archive_words_is_deleted;
    }

    public boolean isArchive_words_is_deleted() {
        return archive_words_is_deleted;
    }

    public void setArchive_words_is_deleted(boolean archive_words_is_deleted) {
        this.archive_words_is_deleted = archive_words_is_deleted;
    }

    public boolean isArchive_words_check() {
        return archive_words_check;
    }

    public void setArchive_words_check(boolean archive_words_check) {
        this.archive_words_check = archive_words_check;
    }

    public String getArchive_words_word() {
        return archive_words_word;
    }

    public void setArchive_words_word(String archive_words_word) {
        this.archive_words_word = archive_words_word;
    }

    public int getArchive_words_user_id() {
        return archive_words_user_id;
    }

    public void setArchive_words_user_id(int archive_words_user_id) {
        this.archive_words_user_id = archive_words_user_id;
    }

    public int getArchive_words_id() {
        return archive_words_id;
    }

    public void setArchive_words_id(int archive_words_id) {
        this.archive_words_id = archive_words_id;
    }
}
