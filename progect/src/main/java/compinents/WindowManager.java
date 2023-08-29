package compinents;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowManager {

    private WebDriver driver;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getAllOpenWindowHandles() {
        return driver.getWindowHandles();
    }

    public void switchToNewWindow() {
        String currentWindowHandle = getCurrentWindowHandle();
        Set<String> openWindows = getAllOpenWindowHandles();
        String newWindowHandle = getNewWindowHandle(openWindows, currentWindowHandle);
        driver.switchTo().window(newWindowHandle);
    }

    private String getNewWindowHandle(Set<String> windowHandles, String currentWindowHandle) {
        windowHandles.remove(currentWindowHandle);
        return windowHandles.iterator().next();
    }
}
