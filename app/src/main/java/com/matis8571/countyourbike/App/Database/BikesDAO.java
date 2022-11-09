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

    @Query("UPDATE bikes SET name = :name, bikeType = :bikeType, brand = :brand, model = :model, mileage = :mileage WHERE ID = :id")
    void update(int id, String name, String bikeType, String brand, String model, String mileage);

    @Delete
    void delete(Bikes bikes);
}
