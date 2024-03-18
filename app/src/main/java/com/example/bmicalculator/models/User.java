package com.example.bmicalculator.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "user_info")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double weight;
    private double height;
    private int age;
    private double bmi;

    private boolean isMale;
    private double bmr;

    private String dateTime;

    private String status;

    public User(double weight, double height, int age, boolean isMale, double bmi, double bmr, String dateTime, String status) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.isMale = isMale;
        this.bmi = bmi;
        this.bmr = bmr;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getBmr() {
        return bmr;
    }

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }
}
