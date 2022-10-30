package com.matis8571.countyourbike.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Add class annotation name, to easily access it from elsewhere later
@Entity(tableName = "notes")
public class Notes implements Serializable {

    // ID of this note generated automatically id every time we add new item
    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    @ColumnInfo(name = "title")
    String title = "";

    @ColumnInfo(name = "notes")
    String notes = "";

    @ColumnInfo(name = "dateAndTime")
    String dateAndTime = "";

    @ColumnInfo(name = "isPinned")
    boolean isPinned = false;

    /* To automatically generate getter and setter for method use
        RMB - Generate (alt + insert) - Getter and Setter - select which ones and press ok */

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
