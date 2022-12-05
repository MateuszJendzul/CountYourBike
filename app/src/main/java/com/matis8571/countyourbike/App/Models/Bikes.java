package com.matis8571.countyourbike.App.Models;

import androidx.annotation.NonNull;
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
    String name = "Bike";

    @ColumnInfo(name = "brand")
    String brand = "Brand";

    @ColumnInfo(name = "model")
    String model = "Model";

    @ColumnInfo(name = "day")
    int day = 0;

    @ColumnInfo(name = "dayToCompare")
    int dayToCompare = 0;

    @ColumnInfo(name = "weekToCompare")
    int weekToCompare = 0;

    @ColumnInfo(name = "monthToCompare")
    int monthToCompare = 0;

    @ColumnInfo(name = "yearToCompare")
    int yearToCompare = 0;

    @ColumnInfo(name = "month")
    int month = 0;

    @ColumnInfo(name = "year")
    int year = 0;

    @ColumnInfo(name = "bikeImageID")
    int bikeImageID = 0;

    @ColumnInfo(name = "bikeImageBoardPosition")
    int bikeImageBoardPosition = 0;

    @ColumnInfo(name = "bikeCreated")
    boolean bikeCreated = false;

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

    public int getBikeImageID() {
        return bikeImageID;
    }

    public void setBikeImageID(int bikeImage) {
        this.bikeImageID = bikeImage;
    }

    public boolean isBikeCreated() {
        return bikeCreated;
    }

    public void setBikeCreated(boolean bikeCreated) {
        this.bikeCreated = bikeCreated;
    }

    public int getBikeImageBoardPosition() {
        return bikeImageBoardPosition;
    }

    public void setBikeImageBoardPosition(int bikeImageBoardPosition) {
        this.bikeImageBoardPosition = bikeImageBoardPosition;
    }

    public int getDayToCompare() {
        return dayToCompare;
    }

    public void setDayToCompare(int dayToCompare) {
        this.dayToCompare = dayToCompare;
    }

    public int getWeekToCompare() {
        return weekToCompare;
    }

    public void setWeekToCompare(int weekToCompare) {
        this.weekToCompare = weekToCompare;
    }

    public int getMonthToCompare() {
        return monthToCompare;
    }

    public void setMonthToCompare(int monthToCompare) {
        this.monthToCompare = monthToCompare;
    }

    public int getYearToCompare() {
        return yearToCompare;
    }

    public void setYearToCompare(int yearToCompare) {
        this.yearToCompare = yearToCompare;
    }

    @NonNull
    @Override
    public String toString() {
        return "Bikes{" +
                "ID=" + ID +
                ", mileage=" + mileage +
                ", kmToday=" + kmToday +
                ", kmThisWeek=" + kmThisWeek +
                ", kmThisMonth=" + kmThisMonth +
                ", kmThisYear=" + kmThisYear +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", day=" + day +
                ", dayToCompare=" + dayToCompare +
                ", weekToCompare=" + weekToCompare +
                ", monthToCompare=" + monthToCompare +
                ", yearToCompare=" + yearToCompare +
                ", month=" + month +
                ", year=" + year +
                ", bikeImageID=" + bikeImageID +
                ", bikeImageBoardPosition=" + bikeImageBoardPosition +
                ", bikeCreated=" + bikeCreated +
                '}';
    }
}
