package compinents;

import page.SubcategoriesPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        WebElement catalogItem = driver.findElement(By.xpath(catalogXpath));
        catalogItem.click();
        return new SubcategoriesPage(driver);
    }
}
