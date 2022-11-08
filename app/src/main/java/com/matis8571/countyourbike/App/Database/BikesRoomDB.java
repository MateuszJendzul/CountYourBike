package com.matis8571.countyourbike.App.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.matis8571.countyourbike.App.Models.Bikes;

@Database(entities = Bikes.class, version = 1, exportSchema = false)
public abstract class BikesRoomDB extends RoomDatabase {
    private static BikesRoomDB bikesRoomDB;
    private static final String DATABASE_NAME = "Bikes";

    public synchronized static BikesRoomDB getInstance(Context context) {
        if (bikesRoomDB == null) {
            bikesRoomDB = Room.databaseBuilder(
                            context.getApplicationContext(), BikesRoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return bikesRoomDB;
    }

    public abstract BikesDAO bikesDAO();
}