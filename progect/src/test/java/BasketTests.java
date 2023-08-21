import static org.junit.jupiter.api.Assertions.assertEquals;
import static page.MainPage.TECHSHOP_URL;

import compinents.ModalDialog;
import page.CardProductPage;
import page.CartPage;
import page.MainPage;
import page.ProductsPage;

import org.junit.jupiter.api.Test;

public class BasketTests extends BaseTest {

    @Test
    void cartCalculatorTest() {
        ProductsPage productsPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getCatalogMenu()
            .selectCatalogItem("Крупная бытовая техника")
            .selectSubcategoriesItem("/catalog/holodilniki");

        CardProductPage cardProductPage = productsPage.clickCardProduct(2);

        double productPrice = cardProductPage.getProductPrice();
        cardProductPage.clickPlusButton();
        cardProductPage.clickAddToCartButton();

        ModalDialog modalDialog = new ModalDialog(getDriver());
        CartPage cartPage = modalDialog.clickGoCartBtn();

        int productQuantity = cartPage.getProductQuantity();
        double totalPrice = cartPage.getTotalPrice();

        assertEquals(productPrice * productQuantity, totalPrice, 0.01);
    }
}
