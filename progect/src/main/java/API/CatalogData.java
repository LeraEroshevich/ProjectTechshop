package API;

import java.util.List;

public class CatalogData {

    private int id;
    private String name;
    private String shardKey;
    private String rawQuery;
    private String query;
    private int type;
    private List<CatalogData> subCategories;
    private int itemsCount;

    public String getName() {
        return name;
    }

    public List<CatalogData> getSubCategories() {
        return subCategories;
    }

    public int getItemsCount() {
        return itemsCount;
    }

}
