package compinents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessSubmissionForm {
    private WebDriver driver;

    public SuccessSubmissionForm(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath ="//div[@class='pweb-alert alert alert-block alert-success fade in']//p")
    private WebElement successMessage;

    public boolean isSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed() && successMessage.getText().equals("Сообщение успешно отправлено");
    }
    public SuccessSubmissionForm getSuccessForm() {
        return new SuccessSubmissionForm(driver);
    }
}
