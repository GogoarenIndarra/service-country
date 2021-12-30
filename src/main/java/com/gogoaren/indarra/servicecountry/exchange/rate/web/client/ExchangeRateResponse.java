package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExchangeRateResponse {

    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("timestamp")
    public Integer timestamp;
    @JsonProperty("base")
    public String base;
    @JsonProperty("date")
    public String date;
    @JsonProperty("rates")
    public Map<String, BigDecimal> rates;
}








