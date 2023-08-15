package page;

import java.time.Duration;
import java.util.List;

import compinents.ProductFilter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    private WebDriver driver;
    @FindBy(xpath = "//div[@class='products']//div[@id='list_product']//div[@class='item']")
    private List<WebElement> productCardLocator;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getProductCardsCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(productCardLocator));

        return productCardLocator.size();
    }

    public ProductFilter getProductFilter() {
        return new ProductFilter(driver);
    }

    public List<WebElement> getProductCards() {
        return productCardLocator;
    }

    public boolean hasProductsInRange(int minPrice, int maxPrice) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(productCardLocator));

        for (WebElement card : productCardLocator) {
            WebElement priceElement = card.findElement(By.xpath(".//div[@class='price last-child']"));
            String priceText = priceElement.getText().replaceAll("[^0-9.]", "");
            double price = Double.parseDouble(priceText);

            if (price < minPrice || price > maxPrice) {
                return false;
            }
        }

        return true;
    }
}
