package com.gogoaren.indarra.servicecountry.conroller;

import com.gogoaren.indarra.servicecountry.country.CountryClient;
import com.gogoaren.indarra.servicecountry.exception.CountryClientException;
import com.gogoaren.indarra.servicecountry.exchange.rate.web.client.ExchangeRate;
import com.gogoaren.indarra.servicecountry.exchange.rate.web.client.ExchangeRateFetcher;
import com.gogoaren.indarra.servicecountry.exchange.rate.web.client.ExchangeRateResponseConverter;
import com.gogoaren.indarra.servicecountry.weather.web.client.Weather;
import com.gogoaren.indarra.servicecountry.weather.web.client.WeatherFetcher;
import com.gogoaren.indarra.servicecountry.ws.client.gen.TCountryInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
@Slf4j
public class CountryController {


    CountryClient client;
    WeatherFetcher weatherFetcher;
    ExchangeRateFetcher exchangeRateFetcher;
    ExchangeRateResponseConverter exchangeRateResponseConverter;

    @GetMapping(value = "/rate/{isoCode}")
    public ExchangeRate getExchangeRate(@PathVariable String isoCode) {
        return exchangeRateResponseConverter
                .convert(exchangeRateFetcher.fetchExchangeRateByCountryCode(isoCode));
    }


    @GetMapping(value = "/countryName/{name}")
    public String getCountryAllDataByCountryName(@PathVariable String name, Model model) {

        String code = client.getIsoCodeFromCountryName(name).getCountryISOCodeResult();
        createGui(code, model);
        return "gui";
    }

    @GetMapping(value = "/countryIsoCode/{code}")
    public String getCountryAllDataByCountryCode(@PathVariable String code, Model model) {

        createGui(code, model);
        return "gui";
    }

    @GetMapping(value = "/{name}")
    public String getAllDataByCountryNameOrCountryCode(@PathVariable String name, Model model) {

        String countryCode;

        if (name.length() > 3) countryCode = client.getIsoCodeFromCountryName(name).getCountryISOCodeResult();
        else countryCode = name;

        createGui(countryCode, model);
        return "gui";
    }

    private void createGui(String code, Model model) {
        TCountryInfo countryData;
        Weather weatherForCapitalCity;

        try {
            countryData = client.getAllData(code).getFullCountryInfoResult();
            weatherForCapitalCity = weatherFetcher.getCountry(countryData.getSCapitalCity());
        } catch (CountryClientException countryClientException) {
            log.error("Could not fetch country information. Exception message: {}", countryClientException.toString());
//            model.addAllAttributes("message", "dfd");//error message here
            return;
        }

        model.addAttribute("name", countryData.getSName());
        model.addAttribute("flag", countryData.getSCountryFlag());
        model.addAttribute("capital", countryData.getSCapitalCity());
        model.addAttribute("weather", weatherForCapitalCity.toString());
        model.addAttribute("ContinentCode", countryData.getSContinentCode());
        model.addAttribute("ISOCode", countryData.getSCurrencyISOCode());
        model.addAttribute("SISOCode", countryData.getSISOCode());
        model.addAttribute("PhonePrefix", countryData.getSPhoneCode());
        model.addAttribute("ExchangeRate", exchangeRateResponseConverter
                .convert(exchangeRateFetcher.fetchExchangeRateByCountryCode(countryData.getSCurrencyISOCode())));
    }

}
