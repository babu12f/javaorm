package com.babor.models;

import jakarta.persistence.*;

@Entity(name = "Contacts")
public class Contact {

    @Id
    private Integer id;

    @Basic(optional = false, fetch = FetchType.EAGER)
    private Name name;

    @Column(name = "NOTES")
    private String notes;

    private boolean starred;

    public Contact() {}

    public Contact(Name name, String notes, boolean starred) {
        this.name = name;
        this.notes = notes;
        this.starred = starred;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }
}
