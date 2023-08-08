package compinents;

import java.time.Duration;

import page.ContactsPage;
import page.ForBuyerPage;
import page.ShippingAndPaymentPage;

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

    public WebElement selectMenuItem(String menuTitle) {
        String menuXpath = String.format("//nav[@class='menu']//a[@title='%s']", menuTitle);
        WebElement menuItem = driver.findElement(By.xpath(menuXpath));
        menuItem.click();
        return menuItem;
    }
    // selectMenuItem("Доставка и оплата").click(); для Теста

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RequestCallModalForm clickBtnRequestCall() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(btnRequestCall)).click();
        return new RequestCallModalForm(driver);
    }

}
