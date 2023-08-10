package API;

import java.util.List;

public class CatalogResponse {

    private int version;

    private List<CatalogData> data;

    public List<CatalogData> getData() {
        return data;
    }

    public int getVersion() {
        return version;
    }
}
