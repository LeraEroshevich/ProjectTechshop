import static page.MainPage.TECHSHOP_URL;

import page.CardProductPage;
import page.MainPage;
import page.ProductsPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BreadCrumbsTest extends BaseTest{

    @Test
    void breadCrumbsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open(TECHSHOP_URL);
        ProductsPage productsPage = mainPage.getCatalogMenu()
            .selectCatalogItem("Телефоны")
            .selectSubcategoriesItem("/catalog/mobilnye-telefony");
        CardProductPage cardProductPage = productsPage.clickCardProduct(4);
        String cardProductUrl = cardProductPage.getCurrentUrl();
        cardProductPage.clickLastBreadcrumb();

        Assertions.assertEquals(cardProductUrl, cardProductPage.getCurrentUrl());

        mainPage = cardProductPage.clickFirstBreadcrumb();
        Assertions.assertEquals(TECHSHOP_URL, mainPage.getCurrentUrl());
    }
}
