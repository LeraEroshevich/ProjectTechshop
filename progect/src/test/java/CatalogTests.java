import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import compinents.ProductFilter;
import page.MainPage;
import page.ProductsPage;

import org.junit.jupiter.api.Test;

public class CatalogTests extends BaseTest {

    @Test
    void outputOfProductCardsTest() {

        ProductsPage subcategoriesPage =
            new MainPage(getDriver()).open(TECHSHOP_URL).getCatalogMenu().selectCatalogItem("Компьютеры и ноутбуки").selectSubcategoriesItem("/catalog/noutbuki-i-netbuki");

        int productCardsCount = subcategoriesPage.getProductCardsCount();
        assertTrue(productCardsCount > 0, "Количество карточек товара равно 0");
    }

    @Test
    void priceFilterTest() {
        ProductsPage productsPage =
            new MainPage(getDriver()).open(TECHSHOP_URL).getCatalogMenu().selectCatalogItem("Компьютеры и ноутбуки").selectSubcategoriesItem("/catalog/noutbuki-i-netbuki");

        ProductFilter productFilter = productsPage.getProductFilter();
        productFilter.applyPriceFilter(1000, 2000);
        productFilter.clickBtnApplyFilter();

        int productsCount = productsPage.getProductCardsCount();
        assertTrue(productsCount > 0, "No products found after applying the filter");

        assertTrue(productsPage.hasProductsInRange(1000, 2000));

    }
}
