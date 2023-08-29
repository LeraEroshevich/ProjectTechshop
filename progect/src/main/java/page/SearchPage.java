package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@id='list_product']//div[@class='title']//a")
    private List<WebElement> productTitles;
    @FindBy(xpath = "//div[@class='search_result last-child']//div[@class='h3 notfound']")
    private WebElement noResultsMessage;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean hasProductCardsWithTitleContainingText(String searchText) {
        if (productTitles.isEmpty()) {
            return false;
        }

        for (WebElement title : productTitles) {
            if (!title.getText().contains(searchText)) {
                return false;
            }
        }
        return true;
    }

    public boolean isNoResultsMessageVisible() {
        return noResultsMessage.isDisplayed();
    }

    public String getNoResultsMessageText() {
        return noResultsMessage.getText();
    }
}
