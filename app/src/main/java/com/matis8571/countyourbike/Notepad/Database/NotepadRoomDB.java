package com.matis8571.countyourbike.Notepad.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.matis8571.countyourbike.Notepad.Models.Notes;

@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class NotepadRoomDB extends RoomDatabase {
    private static NotepadRoomDB database;
    private static final String DATABASE_NAME = "Notepad";

    public synchronized static NotepadRoomDB getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(
                            context.getApplicationContext(), NotepadRoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MainNotepadDAO mainNotepadDAO();
}
