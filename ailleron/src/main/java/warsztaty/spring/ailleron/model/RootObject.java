package warsztaty.spring.ailleron.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootObject {

    @JsonProperty("RestResponse")
    private RestResponse restResponse;

    public RootObject() {
    }

    public RootObject(RestResponse restResponse) {
        this.restResponse = restResponse;
    }

    public RestResponse getRestResponse() {
        return restResponse;
    }

    public void setRestResponse(RestResponse restResponse) {
        this.restResponse = restResponse;
    }
}
