package page;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalePage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@id='list_product']//div[@class='image']//img[@alt='Sale']")
    private List<WebElement> saleLabels;
    @FindBy(xpath = "//select[@id='order']/ancestor::div[@class='controls last-child']")
    private WebElement sortingDropdown;
    @FindBy(xpath = "//div[@id='list_product']//div[@class='title']//a")
    private List<WebElement> productTitles;

    public SalePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean areAllCardsWithSaleLabelDisplayed() {
        for (WebElement saleLabel : saleLabels) {
            if (!saleLabel.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public SalePage selectSortingOption(String optionText) {
        sortingDropdown.click();

        WebElement optionToSelect = driver.findElement(By.xpath("//div[@class='chzn-drop']//li[text()='" + optionText + "']"));
        optionToSelect.click();

        return new SalePage(driver);
    }

    public List<String> getProductTitles() {
        List<String> titles = new ArrayList<>();
        for (WebElement titleElement : productTitles) {
            titles.add(titleElement.getText().trim());
        }
        return titles;
    }

    public boolean areAllProductCardsSorted() {
        List<String> productTitles = getProductTitles();

        List<String> englishTitles = new ArrayList<>();
        List<String> russianTitles = new ArrayList<>();

        for (String title : productTitles) {
            if (isEnglishTitle(title)) {
                englishTitles.add(title);
            } else {
                russianTitles.add(title);
            }
        }

        Collections.sort(englishTitles);
        Collections.sort(russianTitles, Collator.getInstance(new Locale("ru", "RU")));

        List<String> sortedProductTitles = new ArrayList<>(englishTitles);
        sortedProductTitles.addAll(russianTitles);

        return sortedProductTitles.equals(productTitles);
    }

    private boolean isEnglishTitle(String title) {
        return title.matches("^[a-zA-Z0-9]*$");
    }
}
