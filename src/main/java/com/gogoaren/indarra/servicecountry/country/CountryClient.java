package com.gogoaren.indarra.servicecountry.country;

import com.gogoaren.indarra.servicecountry.ws.client.gen.CountryISOCode;
import com.gogoaren.indarra.servicecountry.ws.client.gen.CountryISOCodeResponse;
import com.gogoaren.indarra.servicecountry.ws.client.gen.FullCountryInfo;
import com.gogoaren.indarra.servicecountry.ws.client.gen.FullCountryInfoResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {


    public FullCountryInfoResponse getAllData(String country) {

        FullCountryInfo request = new FullCountryInfo();
        request.setSCountryISOCode(country);
        FullCountryInfoResponse response =
                (FullCountryInfoResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return response;
    }

    public CountryISOCodeResponse getIsoCodeFromCountryName(String country) {

        CountryISOCode request = new CountryISOCode();
        request.setSCountryName(country);
        CountryISOCodeResponse response =
                (CountryISOCodeResponse) getWebServiceTemplate().marshalSendAndReceive(request);

        return response;
    }
}