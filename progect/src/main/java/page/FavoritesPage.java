package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FavoritesPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='wishlist user last-child']//div//div[@class='title last-child']//a")
    private List<WebElement> favoriteProductTitles;

    public FavoritesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductInFavorites(String productName) {
        for (WebElement title : favoriteProductTitles) {
            if (title.getText().trim().equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
