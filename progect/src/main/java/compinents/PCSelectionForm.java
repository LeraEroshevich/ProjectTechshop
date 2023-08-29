package compinents;

import java.time.Duration;

import page.PCSelectionPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PCSelectionForm {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='text last-child']//div[@class='text-center last-child']//a[@href='/podbor-kompyutera']")
    private WebElement goToSelectionBtn;

    public PCSelectionForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PCSelectionPage clickGoToSelectionBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(goToSelectionBtn));

        goToSelectionBtn.click();
        return new PCSelectionPage(driver);
    }
}
