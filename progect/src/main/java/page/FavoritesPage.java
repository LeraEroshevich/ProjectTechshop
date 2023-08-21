package page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FavoritePage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='wishlist  last-child']//div//div[@class='title last-child']//a")
    private List<WebElement> favoriteProductTitles;

    public FavoritePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getFavoriteProductTitles() {
        List<String> titles = new ArrayList<>();
        for (WebElement titleElement : favoriteProductTitles) {
            titles.add(titleElement.getText());
        }
        return titles;
    }
}
