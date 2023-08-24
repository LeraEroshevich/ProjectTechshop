import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import page.MainPage;
import page.SalePage;

import org.junit.jupiter.api.Test;

public class SaleTests extends BaseTest{

    @Test

    void profileEditingTest() {
        SalePage salePage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getFooter()
            .clickSaleLink()
            .selectSortingOption("Названию А-Я");

        assertTrue(salePage.areAllCardsWithSaleLabelDisplayed(), "Not all product cards have Sale label displayed");
        assertTrue(salePage.areAllProductCardsSorted(), "Product cards are not sorted correctly");
    }
}
