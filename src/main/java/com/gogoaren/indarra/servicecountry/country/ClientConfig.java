package com.gogoaren.indarra.servicecountry.country;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfig {

    private static final String CONTEXT_PATH = "com.gogoaren.indarra.servicecountry.ws.client.gen";
    private static final String DEFAULT_URI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

    @Bean
    public Jaxb2Marshaller marshaller() {
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CONTEXT_PATH);
        return marshaller;
    }

    @Bean
    public CountryClient countryFullResponseClient(final Jaxb2Marshaller marshaller) {
        final CountryClient client = new CountryClient();
        client.setDefaultUri(DEFAULT_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}