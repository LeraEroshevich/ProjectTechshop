package API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogItem {

    private int id;
    private String name;
    private String shardKey;
    private String rawQuery;
    private String query;
    private int type;

}
