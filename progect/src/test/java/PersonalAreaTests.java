import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import compinents.LoginForm;
import page.CardProductPage;
import page.FavoritesPage;
import page.MainPage;
import page.PersonalAreaPage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class PersonalAreaTests extends BaseTest {

    @Test
    void profileEditingTest() {
        LoginForm personalAreaPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .clickBtnPersonalArea()
            .fillLoginAndPasswordFields("leraeroshevich99@gmail.com", "Qwer1234!")
            .clickSubmitBtn()
            .getHeader()
            .clickBtnPersonalArea();

        PersonalAreaPage profilePage = new PersonalAreaPage(getDriver())
            .clickBtnProfileEdit()
            .fillProfileFields("TestName", "TestSurname", "11111", "Minsk", "TestStreet", "111F", "55")
            .clickSaveBtn();

        assertTrue(profilePage.isFieldPopulated("name", "TestName TestSurname"));
        assertTrue(profilePage.isFieldPopulated("city", "Minsk"));
        assertTrue(profilePage.isFieldPopulated("index", "11111"));
        assertTrue(profilePage.isFieldPopulated("street", "TestStreet"));
        assertTrue(profilePage.isFieldPopulated("house", "111F"));
        assertTrue(profilePage.isFieldPopulated("apartment", "55"));
    }

    @Test
    void favoriteProductsTest() {
        CardProductPage cardProductPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .clickBtnPersonalArea()
            .fillLoginAndPasswordFields("leraeroshevich99@gmail.com", "Qwer1234!")
            .clickSubmitBtn()
            .getCatalogMenu()
            .selectCatalogItem("Телефоны")
            .selectSubcategoriesItem("/catalog/mobilnye-telefony")
            .clickCardProduct(2);
        String productName = cardProductPage.getProductName().trim();
        cardProductPage.clickFavoriteBtn();
        FavoritesPage favoritesPage = new MainPage(getDriver())
            .getHeader()
            .clickBtnFavorite();

        assertTrue(favoritesPage.isProductInFavorites(productName), "Product is not found in favorites.");
    }
}
