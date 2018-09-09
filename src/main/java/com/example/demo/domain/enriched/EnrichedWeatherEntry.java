package com.example.demo.domain.enriched;

import com.example.demo.domain.WeatherEntry;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.example.demo.utils.WeatherUtils.getCelF;

public class EnrichedWeatherEntry {

    private final WeatherEntry entry;

    public EnrichedWeatherEntry(WeatherEntry entry){
        this.entry = entry;
    }

    public String getTemprature(){
        return getCelF(entry.getTemperature());
    }

    public String getMinTemp(){
        return getCelF(entry.getMinTemperature());
    }

    public String getMaxTemp(){
        return getCelF(entry.getMaxTemperature());
    }

    public LocalDateTime getDate(){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(entry.getTimestamp()), ZoneId.systemDefault());
    }

    public String getCondition(){
        return entry.getCondition();
    }
}
