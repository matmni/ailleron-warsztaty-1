package warsztaty.spring.ailleron.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import warsztaty.spring.ailleron.model.RestResponse;
import warsztaty.spring.ailleron.services.CountryService;

@RestController
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/countries/{code}")
    public RestResponse getCountryDetails(@PathVariable String code) {
        return countryService.getCountryFromRestApi(code);
    }
}
