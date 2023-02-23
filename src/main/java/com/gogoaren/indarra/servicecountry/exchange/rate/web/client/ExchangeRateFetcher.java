package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Slf4j
@AllArgsConstructor
public class ExchangeRateFetcher {

    private final WebClient client;
    private final String apiKey;
    private final String path;

    public ExchangeRateResponse fetchExchangeRateByCountryCode(final String countryCode) {

        final var response = client.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("access_key", apiKey)
                        .queryParam("symbols", countryCode)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .share()
                .block(Duration.ofSeconds(20));
        log.info("Exchange Rate Response for Country Code: {}, Response: {}", countryCode, response);
        return response;
    }
}