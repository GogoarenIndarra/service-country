package com.gogoaren.indarra.servicecountry.country;

import com.gogoaren.indarra.servicecountry.weather.Weather;
import com.gogoaren.indarra.servicecountry.weather.WeatherFetcher;
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
public class MyController {


    CountryClient client;
    WeatherFetcher weatherFetcher;

    @GetMapping(value = "/{code}")
    public String getCountryAllData(@PathVariable String code, Model model) {

        TCountryInfo countryData = client.getAllData(code).getFullCountryInfoResult();
        Weather weatherForCapitalCity = weatherFetcher.getCountry(countryData.getSCapitalCity());

        model.addAttribute("name", countryData.getSName());
        model.addAttribute("flag", countryData.getSCountryFlag());
        model.addAttribute("capital", countryData.getSCapitalCity());
        model.addAttribute("weather", weatherForCapitalCity.toString());
        model.addAttribute("b", countryData.getSContinentCode());
        model.addAttribute("c", countryData.getSCurrencyISOCode());
        model.addAttribute("d", countryData.getSISOCode());
        model.addAttribute("e", countryData.getSPhoneCode());

        return "GUI";
    }

}
