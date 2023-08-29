package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalAreaPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@id='column-left']//a[@title='Изменить данные']")
    private WebElement BtnProfileEdit;
    @FindBy(xpath = "//div[@class='row last-child']//div[@class='h4 name']")
    private WebElement nameField;
    @FindBy(xpath = "//td[contains(., 'Город')]/following-sibling::td")
    private WebElement cityField;
    @FindBy(xpath = "//td[contains(., 'Почтовый индекс')]/following-sibling::td")
    private WebElement indexField;
    @FindBy(xpath = "//td[contains(., 'Улица')]/following-sibling::td")
    private WebElement streetField;
    @FindBy(xpath = "//td[contains(., 'Дом')]/following-sibling::td")
    private WebElement houseField;
    @FindBy(xpath = "//td[contains(., 'Квартира')]/following-sibling::td")
    private WebElement apartmentField;

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProfileEditPage clickBtnProfileEdit() {
        BtnProfileEdit.click();
        return new ProfileEditPage(driver);
    }

    public boolean isFieldPopulated(String fieldTitle, String expectedValue) {
        WebElement fieldElement = getFieldElementByTitle(fieldTitle);
        String actualValue = fieldElement.getText().trim();
        return actualValue.equals(expectedValue);
    }

    private WebElement getFieldElementByTitle(String fieldTitle) {
        switch (fieldTitle.toLowerCase()) {
            case "name":
                return nameField;
            case "city":
                return cityField;
            case "index":
                return indexField;
            case "street":
                return streetField;
            case "house":
                return houseField;
            case "apartment":
                return apartmentField;
            default:
                throw new IllegalArgumentException("Invalid field title: " + fieldTitle);
        }
    }
}
