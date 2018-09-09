package com.example.demo.service;

import com.example.demo.domain.enriched.EnrichedSummary;
import com.example.demo.domain.WeatherEntry;
import com.example.demo.domain.WeeklyWeatherForecast;
import com.example.demo.utils.WeatherUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WeatherUtilsTest {

    @Test
    public void givenWeeklyForecastShouldReturnDailyForecast(){
        WeeklyWeatherForecast forecast = new WeeklyWeatherForecast();
        List<WeatherEntry> entries = new ArrayList<>();
        LocalDateTime tomorrow = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).plusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        entries.add(createWeatherEntry(28,20,25,tomorrow.atZone(zoneId).toInstant().toEpochMilli(),"Cloudy"));
        entries.add(createWeatherEntry(27,21,24,tomorrow.atZone(zoneId).plusHours(3).toInstant().toEpochMilli(),"Rain"));
        entries.add(createWeatherEntry(29,23,25,tomorrow.atZone(zoneId).plusHours(6).toInstant().toEpochMilli(),"Hot"));
        LocalDateTime nextDay = tomorrow.plusDays(1);
        entries.add(createWeatherEntry(28,19,20,nextDay.atZone(zoneId).toInstant().toEpochMilli(),"Rain"));
        entries.add(createWeatherEntry(27,21,24,nextDay.atZone(zoneId).plusHours(3).toInstant().toEpochMilli(),"Hot"));
        entries.add(createWeatherEntry(30,23,25,nextDay.atZone(zoneId).plusHours(6).toInstant().toEpochMilli(),"Hot"));

        forecast.setEntries(entries);

        EnrichedSummary summaryForecast = WeatherUtils.getSummaryForecast(forecast);


        assertEquals("Wrong number of summaries",2,summaryForecast.getEntries().size());

        assertEquals("Wrong temp","25.0C/77.0F",summaryForecast.getEntries().get(0).getTemprature());
        assertEquals("Wrong Min temp","20.0C/68.0F",summaryForecast.getEntries().get(0).getMinTemp());
        assertEquals("Wrong Max temp","29.0C/84.21F",summaryForecast.getEntries().get(0).getMaxTemp());
        assertEquals("Wrong condition","Cloudy",summaryForecast.getEntries().get(0).getCondition());

        assertEquals("Wrong temp","20.0C/68.0F",summaryForecast.getEntries().get(1).getTemprature());
        assertEquals("Wrong Min temp","19.0C/66.21F",summaryForecast.getEntries().get(1).getMinTemp());
        assertEquals("Wrong Max temp","30.0C/86.0F",summaryForecast.getEntries().get(1).getMaxTemp());
        assertEquals("Wrong condition","Rain",summaryForecast.getEntries().get(1).getCondition());

    }



    @Test
    public void givenWeeklyForecastShouldReturnForecastForRequestedDate(){
        WeeklyWeatherForecast forecast = new WeeklyWeatherForecast();
        List<WeatherEntry> entries = new ArrayList<>();
        LocalDateTime tomorrow = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).plusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        entries.add(createWeatherEntry(28,20,25,tomorrow.atZone(zoneId).toInstant().toEpochMilli(),"Cloudy"));
        entries.add(createWeatherEntry(27,21,24,tomorrow.atZone(zoneId).plusHours(3).toInstant().toEpochMilli(),"Rain"));
        entries.add(createWeatherEntry(29,23,25,tomorrow.atZone(zoneId).plusHours(6).toInstant().toEpochMilli(),"Hot"));

        LocalDateTime requestedDate = tomorrow.plusDays(1);
        entries.add(createWeatherEntry(28,19,20,requestedDate.atZone(zoneId).toInstant().toEpochMilli(),"Rain"));
        entries.add(createWeatherEntry(27,21,24,requestedDate.atZone(zoneId).plusHours(3).toInstant().toEpochMilli(),"Hot"));
        entries.add(createWeatherEntry(30,23,25,requestedDate.atZone(zoneId).plusHours(6).toInstant().toEpochMilli(),"Hot"));

        forecast.setEntries(entries);

        EnrichedSummary summaryForecast = WeatherUtils.getSummaryForecast(forecast,requestedDate.toLocalDate());


        assertEquals("Wrong number of summaries",3,summaryForecast.getEntries().size());

        assertEquals("Wrong temp","20.0C/68.0F",summaryForecast.getEntries().get(0).getTemprature());
        assertEquals("Wrong Min temp","19.0C/66.21F",summaryForecast.getEntries().get(0).getMinTemp());
        assertEquals("Wrong Max temp","28.0C/82.41F",summaryForecast.getEntries().get(0).getMaxTemp());
        assertEquals("Wrong condition","Rain",summaryForecast.getEntries().get(0).getCondition());


        assertEquals("Wrong date",requestedDate.toLocalDate(),summaryForecast.getEntries().get(0).getDate().toLocalDate());
        assertEquals("Wrong date",requestedDate.toLocalDate(),summaryForecast.getEntries().get(1).getDate().toLocalDate());
        assertEquals("Wrong date",requestedDate.toLocalDate(),summaryForecast.getEntries().get(2).getDate().toLocalDate());

    }

    private WeatherEntry createWeatherEntry(double max, double min, double temp,long timestamp,String condition) {
        WeatherEntry entryOne = new WeatherEntry();
        entryOne.setCondition(condition);
        entryOne.setMaxTemperature(max);
        entryOne.setMinTemperature(min);
        entryOne.setTemperature(temp);
        entryOne.setTimestamp(timestamp);
        return entryOne;
    }


}
