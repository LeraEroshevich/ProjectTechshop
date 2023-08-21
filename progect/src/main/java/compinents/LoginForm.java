package compinents;

import java.time.Duration;

import page.MainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

    private WebDriver driver;

    @FindBy(xpath = "//form[@id='login-form']//input[@name='username']")
    private WebElement loginInput;
    @FindBy(xpath = "//form[@id='login-form']//input[@name='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//form[@id='login-form']//button[@name='Submit']")
    private WebElement submitBtn;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginForm fillLoginAndPasswordFields(String login, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginInput));

        loginInput.clear();
        loginInput.sendKeys(login);

        passwordInput.clear();
        passwordInput.sendKeys(password);
        return new LoginForm(driver);
    }

    public MainPage clickSubmitBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
        return new MainPage(driver);
    }
}
