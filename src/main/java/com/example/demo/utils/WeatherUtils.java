package com.example.demo.utils;

import com.example.demo.domain.WeatherEntry;
import com.example.demo.domain.WeeklyWeatherForecast;
import com.example.demo.domain.enriched.EnrichedSummary;
import com.example.demo.domain.enriched.EnrichedWeatherEntry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class WeatherUtils {
    public static EnrichedSummary getSummaryForecast(WeeklyWeatherForecast forecast) {
        List<EnrichedWeatherEntry> summaryEntries = new ArrayList<>();
        WeatherEntry summaryEntry=null;
        LocalDate currentDate = LocalDate.now().minusDays(2);
        for (WeatherEntry entry: forecast.getEntries()) {

            LocalDate processingDate =LocalDateTime.ofInstant(Instant.ofEpochMilli(entry.getTimestamp()),
                    TimeZone.getDefault().toZoneId()).toLocalDate();

            if(currentDate.isBefore(processingDate)){
                summaryEntry = new WeatherEntry(entry);
                currentDate = processingDate;
                summaryEntries.add(new EnrichedWeatherEntry(summaryEntry));
            }else {
                summaryEntry.setMaxTemperature(Math.max(summaryEntry.getMaxTemperature(),entry.getMaxTemperature()));
                summaryEntry.setMinTemperature(Math.min(summaryEntry.getMinTemperature(),entry.getMinTemperature()));
            }

        }


        return new EnrichedSummary(forecast.getCity(),summaryEntries);

    }

    public static String getCelF(double temperature) {
        double fe = new BigDecimal((1.8 * temperature) +32).setScale(2, RoundingMode.CEILING).doubleValue();
        return temperature + "C/"+fe+"F";
    }


    public static EnrichedSummary getSummaryForecast(WeeklyWeatherForecast weatherForecast, LocalDate localDate) {
        List<EnrichedWeatherEntry> enrichedWeatherEntries = weatherForecast.getEntries().stream()
                .filter(weatherEntry -> {
                    LocalDate processingDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(weatherEntry.getTimestamp()),
                            TimeZone.getDefault().toZoneId()).toLocalDate();
                    return processingDate.equals(localDate);
                }).map(EnrichedWeatherEntry::new)
                .collect(Collectors.toList());

        return new EnrichedSummary(weatherForecast.getCity(),enrichedWeatherEntries);
    }
}
