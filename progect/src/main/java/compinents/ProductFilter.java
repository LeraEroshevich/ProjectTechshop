package compinents;

import page.ProductsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void applyPriceFilter(int from, int to) {
        priceFromInput.clear();
        priceFromInput.sendKeys(String.valueOf(from));

        priceToInput.clear();
        priceToInput.sendKeys(String.valueOf(to));
    }

    public ProductFilter clickBtnApplyFilter() {
        btnApplyFilter.click();
        return new ProductFilter(driver);
    }
}
