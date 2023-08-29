package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileEditPage {

    private WebDriver driver;

    @FindBy(xpath = "//form[@name='loginForm']//input[@id='f_name']")
    private WebElement nameInput;
    @FindBy(xpath = "//form[@name='loginForm']//input[@id='l_name']")
    private WebElement surnameInput;
    @FindBy(xpath = "//form[@name='loginForm']//input[@id='zip']")
    private WebElement indexInput;
    @FindBy(xpath = "//form[@name='loginForm']//input[@id='city']")
    private WebElement cityInput;
    @FindBy(xpath = "//form[@name='loginForm']//input[@id='street']")
    private WebElement streetsInput;
    @FindBy(xpath = "//form[@name='loginForm']//input[@id='home']")
    private WebElement houseInput;
    @FindBy(xpath = "//form[@name='loginForm']//input[@id='apartment']")
    private WebElement apartmentInput;
    @FindBy(xpath = "//form[@name='loginForm']//input[@type='submit']")
    private WebElement saveBtn;

    public ProfileEditPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProfileEditPage fillProfileFields(String name, String surname, String index, String city, String streets, String house, String apartment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(nameInput));

        nameInput.clear();
        nameInput.sendKeys(name);

        surnameInput.clear();
        surnameInput.sendKeys(surname);

        indexInput.clear();
        indexInput.sendKeys(index);

        cityInput.clear();
        cityInput.sendKeys(city);

        streetsInput.clear();
        streetsInput.sendKeys(streets);

        houseInput.clear();
        houseInput.sendKeys(house);

        apartmentInput.clear();
        apartmentInput.sendKeys(apartment);

        return new ProfileEditPage(driver);
    }

    public PersonalAreaPage clickSaveBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        saveBtn.click();
        return new PersonalAreaPage(driver);
    }

}
