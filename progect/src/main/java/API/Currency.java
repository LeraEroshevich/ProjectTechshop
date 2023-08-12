package API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    private String BYN;

    public String getBYN() {
        return BYN;
    }
}
