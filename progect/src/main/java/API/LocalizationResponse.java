package API;

public class LocalizationResponse {

    private String ogTitle;
    private Country country;
    private Currency currency;

    public Country getCountry() {
        return country;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getOgTitle() {
        return ogTitle;
    }

}
