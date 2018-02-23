package warsztaty.spring.ailleron.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import warsztaty.spring.ailleron.model.RestResponse;
import warsztaty.spring.ailleron.model.RootObject;

import java.util.Locale;

@Service
public class CountryService {
    private static final String COUNTRY_CODE_URL = "http://services.groupkt.com/country/get/iso2code/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageSource messageSource;

    public RestResponse getCountryFromRestApi(String code) {
        ResponseEntity<RootObject> response = getDataFromApi(code);
        if (response.getStatusCode().is2xxSuccessful()) {
            RestResponse restResponse = response.getBody().getRestResponse();
            restResponse.setSayHello(
                    messageSource.getMessage("say.hello", null, Locale.forLanguageTag(code)));
            return restResponse;
        }
        return null;
    }

    private ResponseEntity<RootObject> getDataFromApi(String code) {
        return restTemplate.getForEntity(UriComponentsBuilder.
                fromHttpUrl(COUNTRY_CODE_URL + code).build().toUri(), RootObject.class);
    }

}
