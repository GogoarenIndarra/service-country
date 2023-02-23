package com.gogoaren.indarra.servicecountry.country;

import com.gogoaren.indarra.servicecountry.exception.CountryClientException;
import com.gogoaren.indarra.servicecountry.ws.client.gen.CountryISOCode;
import com.gogoaren.indarra.servicecountry.ws.client.gen.CountryISOCodeResponse;
import com.gogoaren.indarra.servicecountry.ws.client.gen.FullCountryInfo;
import com.gogoaren.indarra.servicecountry.ws.client.gen.FullCountryInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {


    public FullCountryInfoResponse getAllData(final String country) {

        final FullCountryInfo request = new FullCountryInfo();
        request.setSCountryISOCode(country);
        try {
            return (FullCountryInfoResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        } catch (Exception e) {
            log.error("Could not fetch country information. Exception message: {}", e.toString());
            throw new CountryClientException(e.getMessage());
        }
    }

    public CountryISOCodeResponse getIsoCodeFromCountryName(final String country) {
        final CountryISOCode request = new CountryISOCode();
        request.setSCountryName(country);
        try {
            return (CountryISOCodeResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        } catch (Exception e) {
            log.error("Could not fetch country information. Exception message: {}", e.toString());
            throw new CountryClientException(e.getMessage());
        }
    }
}