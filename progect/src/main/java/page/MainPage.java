package page;

import compinents.CatalogMenu;
import compinents.Footer;
import compinents.Header;

import org.openqa.selenium.WebDriver;

public class MainPage {
    public static final String TECHSHOP_URL = "https://techshop.by/";
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public Header getHeader() {
        return new Header(driver);
    }

    public CatalogMenu getCatalogMenu() {
        return new CatalogMenu(driver);
    }

    public Footer getFooter() {
        return new Footer(driver);
    }

    public MainPage open(String url) {
        driver.get(url);
        return new MainPage(driver);
    }
}
