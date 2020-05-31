package net.asurovenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YandexWeather {

    private final RestTemplate restTemplate;

    @Autowired
    public YandexWeather() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    public String getWeatherInLuzino() {
        String url = "https://api.weather.yandex.ru/v1/forecast/?lat=54.57&lon=73.02";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Yandex-API-Key", "6ea86cad-8545-4234-8869-77ec3569395b");

        HttpEntity request = new HttpEntity(headers);

        final ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            int index = body.indexOf("\"temp\":");
            body = body.substring(index, index + 20);
            body = body.substring(body.indexOf(":") + 1);
            body = body.substring(0, body.indexOf(","));
            return body;
        } else {
            return "";
        }
    }

}
