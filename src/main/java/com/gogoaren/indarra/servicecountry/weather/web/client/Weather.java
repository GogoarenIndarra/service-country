package com.gogoaren.indarra.servicecountry.weather.web.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private String city;
    private String temperature;
    private String humidity;
    private String wind;
    private String country;
    private String countryCode;
}