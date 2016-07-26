package com.yo1000.demo.model;

import java.util.Date;

public class Note {
    private Long noteId;
    private String title;
    private String note;
    private Date created;

    public Note() {}

    public Note(String title, String note, Date created) {
        this.title = title;
        this.note = note;
        this.created = created;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

