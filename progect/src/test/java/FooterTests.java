import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import java.util.ArrayList;
import java.util.Set;

import compinents.WindowManager;
import page.MainPage;
import page.VKPage;

import org.junit.jupiter.api.Test;

public class FooterTests extends BaseTest {

    @Test
    void submissionModalFormTest() {
        WindowManager windowManager = new WindowManager(driver);
        VKPage vkPage = new MainPage(driver)
            .open(TECHSHOP_URL)
            .getFooter()
            .clickIconVK();

        windowManager.switchToNewWindow();
        assertEquals("https://vk.com/thetechshop", driver.getCurrentUrl());
        Set<String> allWindowHandles = driver.getWindowHandles();
        driver.close();
        driver.switchTo().window(new ArrayList<>(allWindowHandles).get(0));
        assertTrue(driver.getCurrentUrl().startsWith(TECHSHOP_URL));
    }
}
