package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

public class ExchangeRateResponseConverter {

    public ExchangeRate convert(ExchangeRateResponse exchangeRateResponse) {

        return ExchangeRate.builder()
                .success(exchangeRateResponse.getSuccess())
                .timestamp(exchangeRateResponse.getTimestamp())
                .base(exchangeRateResponse.getBase())
                .date(exchangeRateResponse.getDate())
                .rates(exchangeRateResponse.getRates())
                .build();
    }
}
