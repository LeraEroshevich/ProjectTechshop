import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    private static ChromeOptions options;
    public WebDriver driver;

    @BeforeAll
    static void downloadDriver() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeEach
    void startBrowser() {

        System.setProperty("webdriver.chrome.driver","D:\\QA\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @AfterEach
    void closeDriver() {
        driver.close();
    }
}