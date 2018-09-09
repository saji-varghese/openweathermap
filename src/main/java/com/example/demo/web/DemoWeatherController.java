package com.example.demo.web;



import com.example.demo.domain.enriched.EnrichedSummary;
import com.example.demo.service.OpenWeatherMapService;
import com.example.demo.utils.WeatherUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.demo.utils.WeatherUtils.getSummaryForecast;

@RestController
@RequestMapping("/demo/weather")
public class DemoWeatherController {

    private final OpenWeatherMapService openWeatherMapService;

    public DemoWeatherController(OpenWeatherMapService openWeatherMapService) {
        this.openWeatherMapService = openWeatherMapService;
    }


    @RequestMapping("/weekly/{city}")
    public EnrichedSummary getWeeklyWeatherForecast(@PathVariable String city) {
        return getSummaryForecast(this.openWeatherMapService.getWeatherForecast(city));
    }

    @RequestMapping("/weekly/{city}/{date}")
    public EnrichedSummary getWeeklyWeatherForecast(@PathVariable String city,@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        return WeatherUtils.getSummaryForecast(this.openWeatherMapService.getWeatherForecast(city),localDate);
    }
}