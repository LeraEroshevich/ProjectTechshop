package compinents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartProduct {

    private WebDriver driver;

    public CartProduct(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
