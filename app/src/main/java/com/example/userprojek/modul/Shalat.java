package com.example.userprojek.modul;


import org.threeten.bp.LocalTime;

import java.util.ArrayList;

public class Shalat {
    private String cityCode;
    private String dateToday;
    private ArrayList<String> name;
    private ArrayList<LocalTime> times;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDateToday() {
        return dateToday;
    }

    public void setDateToday(String dateToday) {
        this.dateToday = dateToday;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<LocalTime> times) {
        this.times = times;
    }
}

