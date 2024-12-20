package cz.weatherApp.service;

import cz.weatherApp.City;
import cz.weatherApp.connector.WeatherApiConnector;
import cz.weatherApp.dto.WeatherApiDto;
import cz.weatherApp.dto.WeatherDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public WeatherDto getWeatherForCity(String city) {
        City cityEnum = City.valueOf(city.toUpperCase());
        WeatherApiConnector connector = new WeatherApiConnector();
        WeatherApiDto wdto = connector.getWeatherForCity(cityEnum);
        return transformDto(wdto);
    }

    private WeatherDto transformDto(WeatherApiDto wdto) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setHumidity(wdto.getCurrent().getHumidity()); // vrati humiditu
        weatherDto.setLocation(wdto.getLocation().getName()); // vrati jmeno mesta
        weatherDto.setTemp_celsius(wdto.getCurrent().getTemp_c()); // vrati teplotu
        weatherDto.setTimestamp(wdto.getCurrent().getLast_updated()); // vrati cas
        weatherDto.setWeatherDescription(wdto.getCurrent().getCondition().getText()); // vrati popis
        weatherDto.setWindDirection(wdto.getCurrent().getWind_dir()); // vrati smer vetru
        weatherDto.setWindSpeed_mps(wdto.getCurrent().getWind_kph()/3.6); // vrati rychlost vetru
        weatherDto.setIcon(wdto.getCurrent().getCondition().getIcon());
        return weatherDto;
    }
}
