package compinents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RequestCallModalForm {
    private WebDriver driver;

    private static final String MODAL_FORM_XPATH = "//div[@class='modal-content last-child']//div[@class='modal-body last-child']//form//div[@class='pweb-fields']";

    @FindBy(xpath = MODAL_FORM_XPATH + "//input[@type='text']")
    private WebElement nameField;
    @FindBy(xpath = MODAL_FORM_XPATH + "//input[@type='tel']")
    private WebElement phoneField;
    @FindBy(xpath = MODAL_FORM_XPATH + "//textarea[@name='fields[mess]']")
    private WebElement messageField;
    @FindBy(xpath = MODAL_FORM_XPATH + "//button[@type='button']")
    private WebElement submitButton;

    public RequestCallModalForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public RequestCallModalForm fillForm(String name, String phone, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);

        wait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.sendKeys(phone);

        wait.until(ExpectedConditions.visibilityOf(messageField));
        messageField.sendKeys(message);

        return this;
    }

    public RequestCallModalForm submitForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        return this;
    }

    public boolean isNameFieldRequired() {
        String nameFieldClass = nameField.getAttribute("class");
        return nameFieldClass.contains("required") && nameFieldClass.contains("invalid");
    }

    public boolean isPhoneFieldRequired() {
        String phoneFieldClass = phoneField.getAttribute("class");
        return phoneFieldClass.contains("required") && phoneFieldClass.contains("invalid");
    }
}
