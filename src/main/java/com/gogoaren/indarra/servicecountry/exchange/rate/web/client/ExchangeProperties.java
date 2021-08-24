package com.gogoaren.indarra.servicecountry.exchange.rate.web.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "exchange")
public class ExchangeProperties {

    private String key;
    private String url;

}
