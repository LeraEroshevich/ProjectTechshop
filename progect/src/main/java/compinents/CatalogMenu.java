package compinents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CatalogMenu {
    private WebDriver driver;

    public CatalogMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
