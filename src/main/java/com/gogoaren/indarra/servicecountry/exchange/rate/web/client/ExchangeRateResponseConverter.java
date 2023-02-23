package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

public class ExchangeRateResponseConverter {

    public ExchangeRate convert(final ExchangeRateResponse exchangeRateResponse) {

        return ExchangeRate.builder()
                .success(exchangeRateResponse.getSuccess())
                .timestamp(exchangeRateResponse.getTimestamp())
                .base(exchangeRateResponse.getBase())
                .date(exchangeRateResponse.getDate())
                .countryCode(exchangeRateResponse.getRates().entrySet().stream().findFirst().get().getKey())
                .rate(exchangeRateResponse.getRates().entrySet().stream().findFirst().get().getValue())
                .build();
    }
}
