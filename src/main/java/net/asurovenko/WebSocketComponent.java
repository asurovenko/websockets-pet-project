package net.asurovenko;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Component
public class WebSocketComponent {
    @Bean
    SecurityWebFilterChain httpAuthorization(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    WebSocketHandler webSocketHandler(WeatherService weatherService) {
        return session -> {
            var wsMessage = session
                    .receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .flatMap(msg -> weatherService.getWeather(new WeatherService.ServiceRequest(msg)))
                    .map(x -> session.textMessage(x.getResponseMessage()));
            return session.send(wsMessage);
        };
    }

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler wsh) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/weather", wsh), 10);
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
