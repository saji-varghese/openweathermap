package com.example.demo.service;

import com.example.demo.DemoProperties;
import com.example.demo.domain.DayWeather;
import com.example.demo.domain.WeeklyWeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class OpenWeatherMapService {

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}&units=metric";

    private static final String FORECAST_URL =
            "http://api.openweathermap.org/data/2.5/forecast?q={city}&APPID={key}&units=metric";

    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapService.class);

    private final RestTemplate restTemplate;

    private final String apiKey;

    public OpenWeatherMapService(RestTemplateBuilder restTemplateBuilder,
                                 DemoProperties properties) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = properties.getKey();
    }

    public DayWeather getWeather( String city) {
        logger.info("Requesting current weather for {}", city);
        URI url = new UriTemplate(WEATHER_URL).expand(city, this.apiKey);
        return invoke(url, DayWeather.class);
    }

    public WeeklyWeatherForecast getWeatherForecast(String city) {
        logger.info("Requesting weather forecast for {}", city);
        URI url = new UriTemplate(FORECAST_URL).expand(city, this.apiKey);
        return invoke(url, WeeklyWeatherForecast.class);
    }

    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }

}