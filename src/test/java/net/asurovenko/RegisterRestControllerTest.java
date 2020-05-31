package net.asurovenko;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
@WithMockUser
@Import(WeatherRestController.class)
@RunWith(SpringRunner.class)
class RegisterRestControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private YandexWeather yandexWeather;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        Mockito.when(yandexWeather.getWeatherInLuzino()).thenReturn("15");

        webTestClient
                .get()
                .uri("/api/get")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("@.degrees", "15").exists();
    }

}
