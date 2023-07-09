import static page.MainPage.TECHSHOP_URL;

import compinents.RequestCallModalForm;
import page.MainPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeaderTests extends BaseTest {

    @Test
    void openModalForm() {
        RequestCallModalForm mainPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .clickBtnRequestCall();
        Assertions.assertTrue(mainPage.isWindowModalFormDisplayed(), "Modal window not open");
    }

    @Test
    void pageTransitionCheckTest() {

    }
}
