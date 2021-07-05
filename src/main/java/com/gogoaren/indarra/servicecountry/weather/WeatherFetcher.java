package com.gogoaren.indarra.servicecountry.weather;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
@AllArgsConstructor
public class WeatherFetcher {

    private WebClient webClient;

    public Weather getCountry(String country) {

        return webClient.get()
                .uri("/" + country)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Weather.class)
                .share()
                .block(Duration.ofSeconds(20));

    }
}

