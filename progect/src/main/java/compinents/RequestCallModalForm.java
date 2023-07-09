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

    private static final String MODAL_FORM_XPATH = "//div[@id='pwebcontact107_box']//form[@id='pwebcontact107_form']";

    @FindBy(xpath = "//div[@class='header_contacts last-child']//div[@id='pwebcontact107_toggler']")
    private WebElement windowModalForm;
    @FindBy(xpath = MODAL_FORM_XPATH + "//input[@type='text']")
    private WebElement nameField;
    @FindBy(xpath = MODAL_FORM_XPATH + "//input[@type='tel']")
    private WebElement phoneField;
    @FindBy(xpath = MODAL_FORM_XPATH + "//textarea[@name='fields[mess]']")
    private WebElement messageField;
    @FindBy(xpath = MODAL_FORM_XPATH + "//button[@type='button']")
    private WebElement submitButton;
    @FindBy(xpath ="//div[@class='pweb-alert alert alert-block alert-success fade in']//p")
    private WebElement successMessage;

    public RequestCallModalForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  boolean isWindowModalFormDisplayed(){
        return windowModalForm.isDisplayed();
    }

    public RequestCallModalForm fillName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
        return this;
    }

    public RequestCallModalForm fillPhone(String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.sendKeys(phone);
        return this;
    }

    public RequestCallModalForm fillMessage(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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

    public boolean isSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed() && successMessage.getText().equals("Сообщение успешно отправлено");
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
