package com.example.demo.domain.enriched;

import java.util.List;

public class EnrichedSummary {

    private final List<EnrichedWeatherEntry> weatherEntry;
    private final String city;

    public EnrichedSummary( String city,List<EnrichedWeatherEntry> weatherEntry){
        this.city= city;
        this.weatherEntry=weatherEntry;
    }

    public List<EnrichedWeatherEntry> getEntries() {
        return weatherEntry;
    }

    public String getCity() {
        return city;
    }
}
