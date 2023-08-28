package page;

import compinents.CatalogMenu;
import compinents.Footer;
import compinents.Header;
import compinents.PCSelectionForm;

import org.openqa.selenium.WebDriver;

public class MainPage {
    public static final String TECHSHOP_URL = "https://techshop.by/";
    private static WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public static MainPage open(String url) {
        driver.get(url);
        return new MainPage(driver);
    }

    public Header getHeader() {
        return new Header(driver);
    }

    public CatalogMenu getCatalogMenu() {
        return new CatalogMenu(driver);
    }

    public PCSelectionForm getPCSelectionForm() {
        return new PCSelectionForm(driver);
    }

    public Footer getFooter() {
        return new Footer(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
