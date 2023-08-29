package compinents;

import java.time.Duration;

import page.FinishPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuickOrderForm {

    private WebDriver driver;
    @FindBy(xpath = "//form[@id='quickorder_form']//input[@id='name']")
    private WebElement nameInput;
    @FindBy(xpath = "//form[@id='quickorder_form']//input[@id='phone']")
    private WebElement phoneInput;
    @FindBy(xpath = "//form[@id='quickorder_form']//button[@type='submit']")
    private WebElement submitBtn;

    public QuickOrderForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillNameAndPhoneFields(String name, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(nameInput));

        nameInput.clear();
        nameInput.sendKeys(name);

        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public FinishPage submitOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
        return new FinishPage(driver);
    }
}
