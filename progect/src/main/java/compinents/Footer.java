package compinents;

import java.time.Duration;

import page.VKPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footer {

    private WebDriver driver;

    @FindBy(xpath = "//footer//ul[@class='soc-buttons last-child']//a//span[@class='img fab fa-vk']")
    private WebElement iconVK;

    public Footer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public VKPage clickIconVK() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(iconVK));
        iconVK.click();
        return new VKPage(driver);
    }
}
