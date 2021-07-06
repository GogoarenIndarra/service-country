package com.gogoaren.indarra.servicecountry.weather.web.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherFetcher {

    private final WebClient webClient;
    private final static String ERROR_MESSAGE = "Weather information can not be provided";

    public Weather getCountry(String country) {
        try {
            return webClient.get()
                    .uri("/" + country)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Weather.class)
                    .share()
                    .block(Duration.ofSeconds(20));

        } catch (Exception exception) {
            log.error(ERROR_MESSAGE);
            return new Weather();
        }
    }
}

