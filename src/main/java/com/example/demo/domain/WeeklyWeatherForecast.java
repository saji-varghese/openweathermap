package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeeklyWeatherForecast implements Serializable {

    private String city;

    private List<WeatherEntry> entries = Collections.emptyList();

    public WeeklyWeatherForecast(String city, List<WeatherEntry> summaryEntries) {
        this.city = city;
        this.entries=summaryEntries;
    }

    public WeeklyWeatherForecast(){}

    @JsonProperty("entries")
    public List<WeatherEntry> getEntries() {
        return this.entries;
    }

    @JsonSetter("list")
    public void setEntries(List<WeatherEntry> entries) {
        this.entries = entries;
    }

    @JsonProperty("city")
    public void setCity(Map<String, Object> city) {
        this.city= city.get("name").toString();
    }

    public String getCity() {
        return this.city;
    }


}