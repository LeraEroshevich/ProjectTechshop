package API;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceHistoryEntry {

    private Map<String, Integer> price;

    public Map<String, Integer> getPrice() {
        return price;
    }
}
