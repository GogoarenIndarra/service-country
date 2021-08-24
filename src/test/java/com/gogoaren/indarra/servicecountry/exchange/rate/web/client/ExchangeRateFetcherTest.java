package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeRateFetcherTest {

    private Logger logger = LoggerFactory.getLogger(ExchangeRateFetcher.class);

    @Autowired
    private ExchangeRateFetcher exchangeRateFetcher;

    @Test
    void fetchExchangeRateByCountryCodeTest() {
        ExchangeRateResponse exchangeRateResponse = exchangeRateFetcher.fetchExchangeRateByCountryCode("USD");
        logger.info(exchangeRateResponse.toString());
        assertTrue(true);
    }
}