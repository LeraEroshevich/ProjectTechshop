package compinents;

import java.time.Duration;

import page.ContactsPage;
import page.ForBuyerPage;
import page.ShippingAndPaymentPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='header_contacts last-child']//div[@id='pwebcontact107_toggler']")
    private WebElement btnRequestCall;
    @FindBy(xpath = "//nav[@class='menu']//a[@title='Доставка и оплата']")
    private WebElement shippingAndPaymentItem;
    @FindBy(xpath = "//nav[@class='menu']//a[@title='Для покупателя']")
    private WebElement forBuyerItem;
    @FindBy(xpath = "//nav[@class='menu']//a[@title='Контакты']")
    private WebElement contactsItem;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RequestCallModalForm clickBtnRequestCall() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(btnRequestCall)).click();
        return new RequestCallModalForm(driver);
    }

    public ShippingAndPaymentPage clickShippingAndPaymentItem() {
        shippingAndPaymentItem.click();
        return new ShippingAndPaymentPage(driver);
    }

    public ForBuyerPage clickForBuyerItem() {
        forBuyerItem.click();
        return new ForBuyerPage(driver);
    }

    public ContactsPage clickContactsItem() {
        contactsItem.click();
        return new ContactsPage(driver);
    }
}
