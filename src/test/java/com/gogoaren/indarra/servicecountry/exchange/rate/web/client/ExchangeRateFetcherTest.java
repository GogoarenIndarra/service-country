package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.gogoaren.indarra.servicecountry.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.InputStream;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.gogoaren.indarra.servicecountry.TestUtils.getFileFromResourceAsStream;
import static com.gogoaren.indarra.servicecountry.TestUtils.readFileAsString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles(profiles = "test")
@Slf4j
@SpringBootTest
public class ExchangeRateFetcherTest {

    @Autowired
    private ExchangeRateFetcher exchangeRateFetcher;
    @Rule
    private WireMockRule wireMockRule = new WireMockRule(7001);

    private WebTestClient testClient;

    private String inputString;


    @BeforeEach
    void setUp() throws Exception {

        String inputFile = "exchangeRateFetcherTest.json";
        InputStream inputStream = getFileFromResourceAsStream(inputFile);
        inputString = readFileAsString(inputStream);
        wireMockRule.stubFor(get(urlEqualTo("/api/latest?access_key=secretKey&symbols=PL"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(inputString)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));

        wireMockRule.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockRule.stop();
        wireMockRule.resetAll();
    }

    @Test
    public void statusMessage() {
        var temp = wireMockRule.getRecordingStatus();
        var response = exchangeRateFetcher.fetchExchangeRateByCountryCode("PL");
        log.info(String.valueOf(response));
        Assertions.assertThat(temp.getStatus().equals(200));
    }

    @Test
    void fetchExchangeRateByCountryCodeTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        var expectedResponse =
                objectMapper.readValue(inputString, ExchangeRateResponse.class);

        ExchangeRateResponse exchangeRateResponse =
                exchangeRateFetcher.fetchExchangeRateByCountryCode("PL");

        assertEquals(expectedResponse, exchangeRateResponse);
    }


}