package com.gogoaren.indarra.servicecountry.country;

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
}