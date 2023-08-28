package page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PCSelectionPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='bfPage']//button[@value='далее']")
    private List<WebElement> nextButtons;
    @FindBy(xpath = "//div[@class='bfPage']//button[@value='Отправить']")
    private WebElement btnSend;
    @FindBy(xpath = "//input[@placeholder='Введите ваше имя']")
    private WebElement nameField;
    @FindBy(xpath = "//input[@placeholder='Введите контактный телефон']")
    private WebElement phoneField;
    @FindBy(xpath = "//div[@class='last-child']//label[@id='bfLabel1']")
    private WebElement stepTitle;

    public PCSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PCSelectionPage clickRadioButtonByText(String radioText) {
        WebElement radioButton = driver.findElement(By.xpath("//label[contains(text(), '" + radioText + "')]"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));

        radioButton.click();
        return new PCSelectionPage(driver);
    }

    public PCSelectionPage clickGoToNextStepBtn(int stepIndex) {

        if (stepIndex >= 0 && stepIndex < nextButtons.size()) {
            WebElement nextBtn = nextButtons.get(stepIndex);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(nextBtn));

            nextBtn.click();
        } else {
            throw new IndexOutOfBoundsException("Invalid step index for next button");
        }

        return new PCSelectionPage(driver);
    }

    public PCSelectionPage clickRadioButtonById(String radioButtonId) {
        WebElement radioButton = driver.findElement(By.id(radioButtonId));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));

        radioButton.click();
        return new PCSelectionPage(driver);
    }

    public PCSelectionPage fillNameAndPhoneFields(String name, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOf(phoneField)).sendKeys(phone);

        return this;
    }

    public PCSelectionPage clickBtnSend() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(btnSend));

        btnSend.click();
        return new PCSelectionPage(driver);
    }

    public void assertConfirmationMessageDisplayed(String message) {
        By confirmationMessageLocator = By.xpath("//div[@class='last-child']//h2[contains(text(), '" + message + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessageLocator));
    }

    public PCSelectionPage fillFormAndSubmit() {
        return this.clickRadioButtonByText("Для работы (офисные программы, интернет)").clickGoToNextStepBtn(0).clickRadioButtonByText("AMD Ryzen 7")
            .clickRadioButtonByText("Intel i5 11600KF").clickGoToNextStepBtn(1).clickRadioButtonByText("AMD Radeon RX 5xx").clickGoToNextStepBtn(2).clickRadioButtonByText("DDR3")
            .clickRadioButtonByText("32Гб").clickRadioButtonByText("256Гб").clickRadioButtonByText("4Тб").clickGoToNextStepBtn(3).clickRadioButtonByText("350ватт")
            .clickRadioButtonByText("Бронзовый").clickRadioButtonByText("Midi Tower (средняя башня),высота 43-45см").clickGoToNextStepBtn(4).clickRadioButtonByText("29")
            .clickRadioButtonByText("4k").clickRadioButtonByText("VA").clickGoToNextStepBtn(5).clickRadioButtonById("bfGroupLabel500").clickRadioButtonById("bfGroupLabel501_1")
            .clickRadioButtonById("bfGroupLabel491").clickRadioButtonByText("Нужны, 2.0").clickRadioButtonByText("Лазерный").clickGoToNextStepBtn(6);
    }

    public String getCurrentStepTitle() {
        return stepTitle.getText();
    }

    public boolean isCurrentStepTitle(String expectedTitle) {
        return getCurrentStepTitle().equals(expectedTitle);
    }
}
