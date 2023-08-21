package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FinishPage {

    private WebDriver driver;
    @FindBy(xpath = "//div[@class='section']//div[@class='h2']")
    private WebElement successMessage;

    public FinishPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCorrectURL() {
        return driver.getCurrentUrl().equals("https://techshop.by/oformlenie-zakaza/finish");
    }

    public boolean isSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed() && successMessage.getText().equals("Спасибо за заказ");
    }
}
