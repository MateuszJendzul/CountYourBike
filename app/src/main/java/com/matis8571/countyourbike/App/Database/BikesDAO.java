package com.matis8571.countyourbike.App.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.matis8571.countyourbike.App.Models.Bikes;

import java.util.List;

@SuppressWarnings("AndroidUnresolvedRoomSqlReference")
@Dao
public interface BikesDAO {

    @Insert(onConflict = REPLACE)
    void insert(Bikes bikes);

    @Query("SELECT * FROM bikes ORDER BY id DESC")
    List<Bikes> getAll();

    @Query("UPDATE bikes SET name = :name, bikeType = :bikeType, brand = :brand, model = :model, " +
            "mileage = :mileage, day = :day, month = :month, year = :year, kmToday = :kmToday," +
            "kmThisWeek =:kmThisWeek, kmThisMonth =:kmThisMonth, kmThisYear =:kmThisYear," +
            " imageID =:imageID, bikeCreated =:bikeCreated WHERE ID = :id")
    void update(int id, String name, String bikeType, String brand, String model, int mileage,
                int day, int month, int year, int kmToday, int kmThisWeek, int kmThisMonth,
                int kmThisYear, int imageID, boolean bikeCreated);

    @Delete
    void delete(Bikes bikes);
}