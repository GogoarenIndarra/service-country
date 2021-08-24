package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@EnableConfigurationProperties(ExchangeProperties.class)
@Configuration
@AllArgsConstructor
public class ExchangeRateFetchConfiguration {

    private final ExchangeProperties exchangeProperties;

    @Bean
    ExchangeRateFetcher rateFetcher() {
        WebClient client = WebClient.builder()
                .baseUrl(exchangeProperties.getUrl())
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return new ExchangeRateFetcher(client, exchangeProperties.getKey());
    }

    @Bean
    ExchangeRateResponseConverter exchangeRateResponseConverter() {
        return new ExchangeRateResponseConverter();
    }
}

