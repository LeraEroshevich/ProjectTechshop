package compinents;

import java.time.Duration;

import page.SubcategoriesPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogMenu {
    private WebDriver driver;
    @FindBy(xpath = "//div[@id='column-left']//ul[@class='menu categories accordion']//a[@title='%s']")
    private WebElement catalogItemLocator;

    public CatalogMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SubcategoriesPage selectCatalogItem(String catalogTitle) {
        String catalogXpath = String.format("//div[@id='column-left']//ul[@class='menu categories accordion']//a[@title='%s']", catalogTitle);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement catalogItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(catalogXpath)));
        catalogItem.click();
        return new SubcategoriesPage(driver);
    }
}
