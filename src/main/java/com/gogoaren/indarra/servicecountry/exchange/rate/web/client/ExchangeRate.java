package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class ExchangeRate {

    private Boolean success;
    private Integer timestamp;
    private String base;
    private String date;
    private String countryCode;
    private BigDecimal rate;


}
