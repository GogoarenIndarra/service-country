package com.gogoaren.indarra.servicecountry.country;

import com.gogoaren.indarra.servicecountry.ws.client.gen.FullCountryInfoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class, loader = AnnotationConfigContextLoader.class)
public class FlagClientTest {

    @Autowired
    CountryClient client;

    @Test
    public void flagClientShouldReturnResultWithJpgExtension() {

        FullCountryInfoResponse response = client.getAllData("PLN");
        assertTrue(response.getFullCountryInfoResult().getSCapitalCity().contains("Warsaw"));


    }
}