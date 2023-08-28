import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import compinents.GalleryPopup;
import compinents.ModalDialog;
import compinents.QuickOrderForm;
import page.CardProductPage;
import page.CartPage;
import page.FinishPage;
import page.MainPage;
import page.ProductsPage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class CardProductTests extends BaseTest {

    @Test
    void addProductToCartTest() {
        ProductsPage productsPage = new MainPage(getDriver())
                .open(TECHSHOP_URL)
                .getCatalogMenu()
                .selectCatalogItem("Крупная бытовая техника")
                .selectSubcategoriesItem("/catalog/holodilniki");

        CardProductPage cardProductPage = productsPage.clickCardProduct(2);
        String productName = cardProductPage.getProductName();
        cardProductPage.clickAddToCartButton();

        ModalDialog modalDialog = new ModalDialog(getDriver());
        CartPage cartPage = modalDialog.clickGoCartBtn();

        assertTrue(cartPage.hasProductInCart(productName));
    }

    @Test
    void fastOrderingTest() {
        QuickOrderForm quickOrderForm = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getCatalogMenu()
            .selectCatalogItem("Компьютерная периферия")
            .selectSubcategoriesItem("/catalog/zhk-monitory")
            .clickCardProduct(4)
            .clickBuyInOneClickBtn();

        quickOrderForm.fillNameAndPhoneFields("Test", "111111111");

        FinishPage finishPage = quickOrderForm.submitOrder();

        assertTrue(finishPage.isCorrectURL(), "Incorrect URL on the FinishPage.");
        assertTrue(finishPage.isSuccessMessageDisplayed(), "Success message is not displayed.");
    }

    @Test
    void galleriesTest() {
        ProductsPage productsPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getCatalogMenu()
            .selectCatalogItem("Мелкая бытовая техника")
            .selectSubcategoriesItem("/catalog/pylesosy");

        GalleryPopup galleryPopup = productsPage.clickCardProduct(2)
            .clickGallery();
        String currentImageSrc = galleryPopup.getCurrentImageSrc();

        galleryPopup.clickArrowNext();

        String newImageSrc = galleryPopup.getCurrentImageSrc();

        assertNotEquals(currentImageSrc, newImageSrc, "Images in the gallery are not changing after clicking the arrow next");
    }
}
