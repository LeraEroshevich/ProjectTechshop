import static org.junit.jupiter.api.Assertions.assertEquals;
import static page.MainPage.TECHSHOP_URL;

import compinents.Header;
import compinents.ModalDialog;
import page.CardProductPage;
import page.CartPage;
import page.MainPage;
import page.ProductsPage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class BasketTests extends BaseTest {

    @Test
    void cartCalculatorTest() {
        ProductsPage productsPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getCatalogMenu()
            .selectCatalogItem("Телефоны")
            .selectSubcategoriesItem("/catalog/mobilnye-telefony");
        CardProductPage cardProductPage = productsPage.clickCardProduct(4);

        double productPrice = cardProductPage.getProductPrice();
        cardProductPage.clickPlusButton();
        cardProductPage.clickAddToCartButton();

        ModalDialog modalDialog = new ModalDialog(getDriver());
        CartPage cartPage = modalDialog.clickGoCartBtn();

        int productQuantity = cartPage.getProductQuantity();
        double totalPrice = cartPage.getTotalPrice();

        assertEquals(productPrice * productQuantity, totalPrice, 0.01);
    }

    @Test
    void countProductsTest() {
        ProductsPage productsPage = new MainPage(getDriver())
                .open(TECHSHOP_URL)
                .getCatalogMenu()
                .selectCatalogItem("Крупная бытовая техника")
                .selectSubcategoriesItem("/catalog/holodilniki");

        productsPage.clickBuyButton(0);
        productsPage.getModalDialog().clickContinueShoppingBtn();

        productsPage.clickBuyButton(2);
        productsPage.getModalDialog().clickContinueShoppingBtn();

        Header header = new Header(driver);
        assertEquals("2", header.getCartItemCount(), "Incorrect number of items in the cart");
    }
}
