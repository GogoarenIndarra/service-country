package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@AllArgsConstructor
public class ExchangeRateFetcher {

    private WebClient client;
    private String apiKey;


    public ExchangeRateResponse fetchExchangeRateByCountryCode(String countryCode) {

        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/")
                        .queryParam("access_key", apiKey)
                        .queryParam("symbols", countryCode)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .share()
                .block(Duration.ofSeconds(20));

    }
}
