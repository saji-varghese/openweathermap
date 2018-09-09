package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class WeatherEntry implements Serializable {

    private long timestamp;

    private double temperature;

    private double minTemperature;

    private double maxTemperature;

    private String condition;

    public WeatherEntry(){}
    public WeatherEntry(WeatherEntry weatherEntry){
        this.timestamp = weatherEntry.timestamp;
        this.temperature = weatherEntry.temperature;
        this.minTemperature = weatherEntry.minTemperature;
        this.maxTemperature = weatherEntry.maxTemperature;
        this.condition = weatherEntry.condition;
    }


    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonSetter("dt")
    public void setDt(long unixTime) {
        this.timestamp = unixTime * 1000;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Return the temperature in Celsius (C).
     */
    public double getTemperature() {
        return this.temperature;
    }


    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMinTemperature() {
        return this.minTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMaxTemperature() {
        return this.maxTemperature;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }


    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemperature(Double.parseDouble(main.get("temp").toString()));
        setMinTemperature(Double.parseDouble(main.get("temp_min").toString()));
        setMaxTemperature(Double.parseDouble(main.get("temp_max").toString()));
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setCondition((String) weather.get("description"));
    }
}







