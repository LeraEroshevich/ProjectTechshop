package API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategory {
    private int id;
    private String name;
    private String shardKey;
    private String rawQuery;
    private String query;
    private int type;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
