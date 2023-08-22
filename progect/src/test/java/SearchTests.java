import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import page.MainPage;
import page.SearchPage;

import org.junit.jupiter.api.Test;

public class SearchTests extends BaseTest {

    @Test
    void successfulSearchTest() {
        SearchPage searchPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .fillSearchInput("Кабель")
            .clickBtnSearch();

        assertTrue(searchPage.hasProductCardsWithTitleContainingText("Кабель"));
    }

    @Test
    void unsuccessfulSearchTest() {
        SearchPage searchPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .fillSearchInput("rererer")
            .clickBtnSearch();

        assertTrue(searchPage.isNoResultsMessageVisible());
        assertEquals("Ничего не найдено", searchPage.getNoResultsMessageText());
    }
}
