package cz.weatherApp.controller;

import cz.weatherApp.City;
import cz.weatherApp.dto.WeatherDto;
import cz.weatherApp.service.WeatherService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping({"/weather", "/weather/"})
    @CrossOrigin
    public Collection<WeatherDto> getWeather() {
        List<WeatherDto> weatherDtoList = new ArrayList<>();
        for (City cityEnum : City.values()) {
            weatherDtoList.add(weatherService.getWeatherForCity(cityEnum.toString()));
        }
        return weatherDtoList;
    }
    @CrossOrigin
    @GetMapping({"/weather/{city}", "/weather/{city}/"})
    public WeatherDto getWeatherForCity(@PathVariable("city") @Parameter(name="city", description="Vyhledejte mista jako: ULAANBAATAR, HOVSGOL, SOUL, GUJARAT, MAHARASHTRA, KASHMIR, LIMA, LUKLA, ALMATY, GERALDTON, BANDUNDU, USHUAIA, INUVIK, CHIBA, SUNCHEON, COVENTRY") String city) {
       return weatherService.getWeatherForCity(city);
    }
}
