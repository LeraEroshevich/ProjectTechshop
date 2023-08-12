package API;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalizationResponse {

    private String ogTitle;
    private String lang;
    private String ogLocale;
    private String ogDescription;
    private Map<String, String> currency;
    private Map<String, String> country;

    public Map<String, String> getCountry() {
        return country;
    }

    public Map<String, String> getCurrency() {
        return currency;
    }

    public String getOgTitle() {
        return ogTitle;
    }

}
