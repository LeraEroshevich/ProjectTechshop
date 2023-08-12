package API;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogData {

    private int id;
    private String name;
    private String shardKey;
    private String rawQuery;
    private String query;
    private int type;
    private List<SubCategory> nodes;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SubCategory> getNodes() {
        return nodes;
    }

}
