package com.example.demo.domain;

public class DayWeather extends WeatherEntry {

    private String name;

    public String getCity() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}