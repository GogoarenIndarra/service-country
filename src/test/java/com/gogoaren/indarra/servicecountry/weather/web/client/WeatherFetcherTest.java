package com.gogoaren.indarra.servicecountry.weather.web.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.InputStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.gogoaren.indarra.servicecountry.TestUtils.getFileFromResourceAsStream;
import static com.gogoaren.indarra.servicecountry.TestUtils.readFileAsString;

@Slf4j
@SpringBootTest
@ActiveProfiles(profiles = "test")
class WeatherFetcherTest {

    @Autowired
    private WeatherFetcher weatherFetcher;

    @Rule
    private static final WireMockRule wireMockServer7001 = new WireMockRule(7001);

    @BeforeEach
    void setUp() throws Exception {

        String weatherFetcherPath = ".*";
        String weatherFetcherFile = "weatherResponse.json";
        InputStream weatherFetcherInputStream = getFileFromResourceAsStream(weatherFetcherFile);
        var weatherFetcherString = readFileAsString(weatherFetcherInputStream);

        wireMockServer7001.stubFor(get(urlPathMatching(weatherFetcherPath))
                .willReturn(aResponse().withStatus(200)
                        .withBody(weatherFetcherString)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));

        wireMockServer7001.start();

    }

    @AfterEach
    void tearDown() {
        wireMockServer7001.stop();
        wireMockServer7001.shutdown();
    }

    @Test
    void getCountry() {

        var temp = weatherFetcher.getCountry("PL");
        log.info(String.valueOf(temp));
    }
}