package com.example.demo.service;

import com.example.demo.domain.DayWeather;
import com.example.demo.domain.WeeklyWeatherForecast;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(OpenWeatherMapService.class)
@TestPropertySource(properties = "demo.weather.key=demo")
public class OpenWeatherMapServiceTest {

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void givenResponseForDayForecastShouldReturnDayWeather(){
        server.expect(
                requestTo("http://api.openweathermap.org/data/2.5/weather?q=London&APPID=demo&units=metric"))
                .andRespond(withSuccess(
                        new ClassPathResource("London.json"),
                        MediaType.APPLICATION_JSON));

        DayWeather weather = openWeatherMapService.getWeather( "London");

        assertEquals("City name is wrong", "London",weather.getCity());
        assertEquals("Condition is wrong", "scattered clouds",weather.getCondition());
        assertEquals("Temp is wrong", 14.54,weather.getTemperature(),0);
        assertEquals("Min Temp is wrong", 13.0,weather.getMinTemperature(),0);
        assertEquals("Max Temp is wrong", 16.0,weather.getMaxTemperature(),0);

    }

    @Test
    public void givenResponseForWeeklyForecastShouldReturnWeeklyWeather() {
        server.expect(
                requestTo("http://api.openweathermap.org/data/2.5/forecast?q=London&APPID=demo&units=metric"))
                .andRespond(withSuccess(
                        new ClassPathResource("London_Forecast.json"),
                        MediaType.APPLICATION_JSON));

        WeeklyWeatherForecast weather = openWeatherMapService.getWeatherForecast( "London");

        assertEquals("City name is wrong", "London",weather.getCity());
        assertEquals("Wrong number of eateries", 39,weather.getEntries().size());


    }
}