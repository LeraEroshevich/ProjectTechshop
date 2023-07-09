package page;

import compinents.CatalogMenu;
import compinents.Header;

import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public static final String TECHSHOP_URL = "https://techshop.by/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public Header getHeader() {
        return new Header(driver);
    }
    public CatalogMenu getCatalogMenu() {
        return new CatalogMenu(driver);
    }

    public MainPage open(String url) {
        driver.get(url);
        return new MainPage(driver);
    }
}
