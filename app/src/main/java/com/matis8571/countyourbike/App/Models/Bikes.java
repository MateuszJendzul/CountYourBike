package com.matis8571.countyourbike.App.Models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Bikes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int ID = 1;

    @ColumnInfo(name = "name")
    String name = "";

    @ColumnInfo(name = "bikeType")
    String bikeType = "";

    @ColumnInfo(name = "brand")
    String brand = "";

    @ColumnInfo(name = "model")
    String model = "";

    @ColumnInfo(name = "mileage")
    String mileage = "";

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
