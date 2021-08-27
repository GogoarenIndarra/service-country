package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.gogoaren.indarra.servicecountry.TestUtils;
import org.checkerframework.checker.units.qual.A;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExchangeRateFetcherTest {
    //TODO
/* Add properties test profile */
    // @ActiveProfiles("test")

    @Autowired
    public ExchangeRateFetcher exchangeRateFetcher;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);


    @Test
    public void statusMessage() throws Exception {
        wireMockRule.stubFor(get(urlEqualTo("data.fixer.io/api/latest"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(TestUtils.readFileAsString("src/test/resourcesTest/inputForTest_ExchangeRateFetcherTest.json"))
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));

    }

    @Test
    void fetchExchangeRateByCountryCodeTest() throws Exception {

        wireMockRule.stubFor(get(urlEqualTo("data.fixer.io/api/latest"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(TestUtils.readFileAsString("src/test/resourcesTest/inputForTest_ExchangeRateFetcherTest.json"))
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));

        String file =
                "src/test/resourcesTest/inputForTest_ExchangeRateFetcherTest.json";
        String jsonResponse = TestUtils.readFileAsString(file);
        ObjectMapper objectMapper = new ObjectMapper();
        var expectedResponse =
                objectMapper.readValue(jsonResponse, ExchangeRateResponse.class);

        ExchangeRateResponse exchangeRateResponse =
                exchangeRateFetcher.fetchExchangeRateByCountryCode("USD");

        assertEquals(expectedResponse, exchangeRateResponse);
    }


}