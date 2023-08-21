package page;

import compinents.QuickOrderForm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardProductPage {

    private WebDriver driver;
    @FindBy(xpath = "//div[@class='productfull last-child']//h1[@id='item_title']")
    private WebElement productNameElement;
    @FindBy(xpath = "//div[@class='productfull last-child']//div[@class='buy_block']//button[@value='В корзину']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//form[@name='product']//div[@class='prices pricebox']//div[@class='price']")
    private WebElement productPriceElement;
    @FindBy(xpath = "//form[@name='product']//div[@class='prod_qty']//span[@class='plus ti-plus']")
    private WebElement plusButton;
    @FindBy(xpath = "//form[@name='product']//div[@class='product-buttons last-child']//button[@type='button']")
    private WebElement buyInOneClickBtn;
    public CardProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return productNameElement.getText();
    }

    public CardProductPage clickAddToCartButton() {
        addToCartButton.click();
        return new CardProductPage(driver);
    }

    public double getProductPrice() {
        String priceText = productPriceElement.getText();
        String priceValue = priceText.replaceAll("[^\\d.]", "");
        return Double.parseDouble(priceValue);
    }

    public CardProductPage clickPlusButton() {
        plusButton.click();
        return new CardProductPage(driver);
    }

    public QuickOrderForm clickBuyInOneClickBtn() {
        buyInOneClickBtn.click();
        return new QuickOrderForm(driver);
    }

}
