package cz.weatherApp.connector;

import cz.weatherApp.City;
import cz.weatherApp.dto.WeatherApiDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class WeatherApiConnector {
    // https://api.weatherapi.com/v1/current.json?key=3499d454bb744909b3a65517241612&q=Ulaanbaatar&aqi=no
    // https://api.weatherapi.com/v1/forecast.json?key=3499d454bb744909b3a65517241612&q=Ulaanbaatar&days=1&aqi=no&alerts=no

    // stranky jsou rozpitvane kvuli snadnejsi udrzitelnosti a pripadne uprave

    private static String baseUrl = "https://api.weatherapi.com/v1/";
    private static String urlParams = "current.json?key=";
    private static String apiKey = "3499d454bb744909b3a65517241612";
    private static String url = baseUrl + urlParams + apiKey + "&q=";

    public WeatherApiDto getWeatherForCity(City city) {
        RestTemplate template = new RestTemplate();
        URI uri = null;
        try {
            uri = new URI(url + city);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<WeatherApiDto> response = template.getForEntity(uri, WeatherApiDto.class); // vraci json
        return response.getBody(); // vraci json z API weatherapi
    }
}
