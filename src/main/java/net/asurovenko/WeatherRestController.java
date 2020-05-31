package net.asurovenko;

import net.asurovenko.models.WeaterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherRestController {

    @Autowired
    private YandexWeather yandexWeather;

    @GetMapping("/api/get")
    public WeaterModel getWeather() {
        return new WeaterModel(yandexWeather.getWeatherInLuzino());
    }
}
