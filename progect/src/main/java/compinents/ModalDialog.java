package compinents;

import java.time.Duration;

import page.CartPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalDialog {

    private WebDriver driver;
    @FindBy(xpath = "//div[@class='modal-dialog']//button[@class='btn btn-primary']")
    private WebElement goCartBtn;

    public ModalDialog(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage clickGoCartBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(goCartBtn));

        goCartBtn.click();
        return new CartPage(driver);
    }

}
