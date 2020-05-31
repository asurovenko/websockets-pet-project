package net.asurovenko;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.text.DateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

@Controller
class WeatherService {

    @Autowired
    private YandexWeather yandexWeather;

    private DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.ENGLISH);


    @MessageMapping("weather")
    Flux<ServiceResponse> getWeather(ServiceRequest request) {
        return Flux
                .fromStream(Stream.generate(() -> new ServiceResponse(
                        String.format("Right now %s℃, last check %s", yandexWeather.getWeatherInLuzino(), dateFormat.format(new Date())))))
                .delayElements(Duration.ofSeconds(3));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServiceRequest {
        private String requestMessage;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ServiceResponse {
        private String responseMessage;

    }

}
