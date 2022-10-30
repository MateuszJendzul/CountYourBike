package com.matis8571.countyourbike.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.matis8571.countyourbike.Models.Notes;

import java.util.List;

@Dao
public interface MainNotepadDAO {

    // Object of Notes.class
    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    /* Select all items from tableName "notes"
     sort added notes in descending order (newly added notes on the top) */
    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Notes> getAll();

    // Update the items inside of the parameter (@ColumnInfo values from the Notes.class)
    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :id")
    void update(int id, String title, String notes);

    // Method to delete items from the database
    @Delete
    void delete(Notes notes);
}
