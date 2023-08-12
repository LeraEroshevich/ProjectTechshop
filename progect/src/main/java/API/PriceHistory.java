package API;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceHistory {

    private List<PriceHistoryEntry> data;

    public List<PriceHistoryEntry> getData() {
        return data;
    }
}
