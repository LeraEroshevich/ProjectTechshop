package compinents;

import java.time.Duration;

import page.FavoritesPage;
import page.SearchPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private WebDriver driver;
    @FindBy(xpath = "//div[@class='header_top container']//div[@class='header_contacts last-child']//span")
    private WebElement btnRequestCall;
    @FindBy(xpath = "//div[@class='account']//div[@class='user_login']")
    private WebElement btnPersonalArea;
    @FindBy(xpath = "//div[@class='account']//div[@class='fav last-child']")
    private WebElement btnFavorite;
    @FindBy(xpath = "//div[@class='row last-child']//input[@name='search']")
    private WebElement searchInput;
    @FindBy(xpath = "//div[@class='row last-child']//form[@name='searchForm']//button[@type='submit']")
    private WebElement btnSearch;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // selectMenuItem("Доставка и оплата").click(); для Теста

    public WebElement selectMenuItem(String menuTitle) {
        String menuXpath = String.format("//nav[@class='menu']//a[@title='%s']", menuTitle);
        WebElement menuItem = driver.findElement(By.xpath(menuXpath));
        menuItem.click();
        return menuItem;
    }

    public RequestCallModalForm clickBtnRequestCall() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(btnRequestCall)).click();
        return new RequestCallModalForm(driver);
    }

    public LoginForm clickBtnPersonalArea() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(btnPersonalArea));
        btnPersonalArea.click();
        return new LoginForm(driver);
    }

    public FavoritesPage clickBtnFavorite() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(btnPersonalArea));
        btnFavorite.click();
        return new FavoritesPage(driver);
    }

    public Header fillSearchInput(String text) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchInput));

        searchInput.clear();
        searchInput.sendKeys(text);
        return new Header(driver);
    }

    public SearchPage clickBtnSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(btnSearch));

        btnSearch.click();
        return new SearchPage(driver);
    }

}

