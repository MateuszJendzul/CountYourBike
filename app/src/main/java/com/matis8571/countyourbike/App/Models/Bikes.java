package com.matis8571.countyourbike.App.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "bikes")
public class Bikes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    @ColumnInfo(name = "mileage")
    int mileage = 0;

    @ColumnInfo(name = "kmToday")
    int kmToday = 0;

    @ColumnInfo(name = "kmThisWeek")
    int kmThisWeek = 0;

    @ColumnInfo(name = "kmThisMonth")
    int kmThisMonth = 0;

    @ColumnInfo(name = "kmThisYear")
    int kmThisYear = 0;

    @ColumnInfo(name = "name")
    String name = "";

    @ColumnInfo(name = "bikeType")
    String bikeType = "";

    @ColumnInfo(name = "brand")
    String brand = "";

    @ColumnInfo(name = "model")
    String model = "";

    @ColumnInfo(name = "day")
    int day = 0;

    @ColumnInfo(name = "month")
    int month = 0;

    @ColumnInfo(name = "year")
    int year = 0;

    public int getKmToday() {
        return kmToday;
    }

    public void setKmToday(int kmToday) {
        this.kmToday = kmToday;
    }

    public int getKmThisWeek() {
        return kmThisWeek;
    }

    public void setKmThisWeek(int kmThisWeek) {
        this.kmThisWeek = kmThisWeek;
    }

    public int getKmThisMonth() {
        return kmThisMonth;
    }

    public void setKmThisMonth(int kmThisMonth) {
        this.kmThisMonth = kmThisMonth;
    }

    public int getKmThisYear() {
        return kmThisYear;
    }

    public void setKmThisYear(int kmThisYear) {
        this.kmThisYear = kmThisYear;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
