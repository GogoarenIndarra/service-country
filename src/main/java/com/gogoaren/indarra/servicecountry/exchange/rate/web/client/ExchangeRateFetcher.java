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


    public ExchangeRateResponse fetchExchangeRateByCountryCode(String countryCode) {

        var response = client.get()
                .uri(uriBuilder -> uriBuilder.path("")
                        .queryParam("access_key", apiKey)
                        .queryParam("symbols", countryCode)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .share()
                .block(Duration.ofSeconds(200));
        log.info("Exchange Rate Response for Country Code: " + countryCode + " Response: " + response);
        return response;

    }
}
