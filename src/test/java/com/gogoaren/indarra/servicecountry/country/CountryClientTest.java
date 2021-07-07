package com.gogoaren.indarra.servicecountry.country;

import com.gogoaren.indarra.servicecountry.ws.client.gen.CountryISOCodeResponse;
import com.gogoaren.indarra.servicecountry.ws.client.gen.FullCountryInfoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class, loader = AnnotationConfigContextLoader.class)
public class CountryClientTest {

    @Autowired
    CountryClient client;

    @Test
    public void countryClientShouldReturnResultWithJpgExtension() {

        FullCountryInfoResponse response = client.getAllData("PL");
        assertTrue(response.getFullCountryInfoResult().getSCountryFlag().contains("Flags/Poland.jpg"));
    }

    @Test
    public void shouldReturnISocodefromCountryName() {

        String result = client.getIsoCodeFromCountryName("Poland").getCountryISOCodeResult();
        assertEquals(result, "PL");
    }
}