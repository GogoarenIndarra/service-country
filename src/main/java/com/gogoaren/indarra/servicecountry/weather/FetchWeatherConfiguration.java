package com.gogoaren.indarra.servicecountry.weather;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class FetchWeatherConfiguration {

    @Bean
    WebClient getWebClient() {

        return WebClient.builder()
                .baseUrl("http://localhost:8000/api/weather")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
