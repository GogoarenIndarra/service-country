package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ExchangeRateFetcherTest {

    @Autowired
    public ExchangeRateFetcher exchangeRateFetcher;

    @Test
    void fetchExchangeRateByCountryCodeTest() throws Exception {
        String file =
                "src/test/resourcesTest/inputForTest_ExchangeRateFetcherTest.json";
        String jsonResponse = readFileAsString(file);
        ObjectMapper objectMapper = new ObjectMapper();
        var expectedResponse =
                objectMapper.readValue(jsonResponse, ExchangeRateResponse.class);

        ExchangeRateResponse exchangeRateResponse =
                exchangeRateFetcher.fetchExchangeRateByCountryCode("USD");

        assertEquals(expectedResponse, exchangeRateResponse);
    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}