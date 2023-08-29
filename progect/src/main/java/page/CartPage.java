package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;
    @FindBy(xpath = "//div[@class='item']")
    private List<WebElement> cartProducts;
    @FindBy(xpath = "//div[@class='item']//div[@class='name']//a")
    private WebElement productTitle;
    @FindBy(xpath = "//div[@class='quantity']//input[@class='number plusminus']")
    private WebElement productQuantityElement;
    @FindBy(xpath = "//div[@class='carttotal']//span[@class='price last-child']")
    private WebElement totalPriceElement;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getProductQuantity() {
        String quantityText = productQuantityElement.getAttribute("value");
        return Integer.parseInt(quantityText);
    }

    public double getTotalPrice() {
        String totalPriceText = totalPriceElement.getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(totalPriceText);
    }

    public boolean hasProductInCart(String productName) {
        return productTitle.getText().contains(productName);
    }

    public List<WebElement> getCartProducts() {
        return cartProducts;
    }

}
