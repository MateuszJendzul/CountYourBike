package com.matis8571.countyourbike.App.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

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

    @Query("UPDATE bikes SET name = :name, brand = :brand, model = :model, " +
            "mileage = :mileage, day = :day, month = :month, year = :year, kmToday = :kmToday," +
            "kmThisWeek =:kmThisWeek, kmThisMonth =:kmThisMonth, kmThisYear =:kmThisYear," +
            "bikeImageID =:bikeImageID, bikeImageBoardPosition =:bikeImageBoardPosition," +
            "bikeCreated =:bikeCreated, dayToCompare =:dayToCompare, weekToCompare =:weekToCompare," +
            "monthToCompare =:monthToCompare, yearToCompare =:yearToCompare WHERE ID = :id")
    void update(int id, String name, String brand, String model, int mileage,
                int day, int month, int year, int kmToday, int kmThisWeek, int kmThisMonth,
                int kmThisYear, int bikeImageID, int bikeImageBoardPosition, boolean bikeCreated,
                int dayToCompare, int weekToCompare, int monthToCompare, int yearToCompare);

    @Delete
    void delete(Bikes bikes);
}