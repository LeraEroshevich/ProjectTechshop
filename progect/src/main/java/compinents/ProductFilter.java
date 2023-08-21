package compinents;

import java.time.Duration;

import page.ProductsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductFilter {

    private WebDriver driver;

    public ProductFilter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='modules_top']//input[@id='price_from']")
    private WebElement priceFromInput;
    @FindBy(xpath = "//div[@class='modules_top']//input[@id='price_to']")
    private WebElement priceToInput;
    @FindBy(xpath = "//div[@class='modules_top']//div[@class='uf_buttons']//button[@type='submit']")
    private WebElement btnApplyFilter;
    @FindBy(xpath = "//div[@id='unijax_filter']//li[@class='search-field']//input[@type='text']")
    private WebElement fieldManufacturer;
    @FindBy(xpath = "//ul[@class='chzn-results']//li[contains(text(), 'APPLE')]")
    private WebElement appleFilter;

    public void applyPriceFilter(int from, int to) {
        priceFromInput.clear();
        priceFromInput.sendKeys(String.valueOf(from));

        priceToInput.clear();
        priceToInput.sendKeys(String.valueOf(to));
    }

    public ProductFilter clickFieldManufacturer() {
        fieldManufacturer.click();
        return new ProductFilter(driver);
    }
    public ProductFilter clickAppleFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(appleFilter));

        appleFilter.click();
        return this;
    }

    public ProductFilter clickBtnApplyFilter() {
        btnApplyFilter.click();
        return new ProductFilter(driver);
    }
}
