package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;


class ExchangeRateResponseConverterTest {

    ExchangeRateResponseConverter exchangeRateResponseConverter =
            new ExchangeRateResponseConverter();

    @Test
    void convertTest() throws JsonProcessingException {

        ExchangeRate exchangeRateExample = ExchangeRate.builder()
                .success(true)
                .timestamp(1629797283)
                .base("EUR")
                .date("2021-08-24")
                .rates(Map.of("PLN", BigDecimal.valueOf(4.58131),
                        "USD", BigDecimal.valueOf(1.17344)))
                .build();

        Assertions.assertEquals(exchangeRateExample,
                exchangeRateResponseConverter.convert(createExchangeRateResponse()));
    }

    private ExchangeRateResponse createExchangeRateResponse() throws JsonProcessingException {
        //TODO move to file
        String jsonResponse =
                "{\n" +
                        "    \"success\": true,\n" +
                        "    \"timestamp\": 1629797283,\n" +
                        "    \"base\": \"EUR\",\n" +
                        "    \"date\": \"2021-08-24\",\n" +
                        "    \"rates\": {\n" +
                        "        \"USD\": 1.17344,\n" +
                        "        \"PLN\": 4.58131\n" +
                        "    }\n" +
                        "}";

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, ExchangeRateResponse.class);

    }
}