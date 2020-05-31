package net.asurovenko;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

@Log4j2
@SpringBootApplication(scanBasePackages = "net.asurovenko")
@Configuration
@EnableWebFlux
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
