package compinents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GalleryPopup {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='modal-content']//img[@class='img-fluid']")
    private WebElement galleryImage;

    @FindBy(xpath = "//div[@class='modal-content']//div[@class='ekko-lightbox-nav-overlay']//span[@class='glyphicon glyphicon-chevron-right']")
    private WebElement nextButton;

    public GalleryPopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentImageSrc() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(galleryImage));
        return galleryImage.getAttribute("src");
    }

    public void clickArrowNext() {
        nextButton.click();
    }
}

