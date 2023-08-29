package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SubcategoriesPage {

    private WebDriver driver;

    public SubcategoriesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage selectSubcategoriesItem(String subcategoriesHref) {
        String subcategoriesXpath = String.format("//ul[@class='sub_categories']//li//a[@href='%s']", subcategoriesHref);
        WebElement subcategoriesItem = driver.findElement(By.xpath(subcategoriesXpath));
        subcategoriesItem.click();
        return new ProductsPage(driver);
    }
}
