import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import compinents.SuccessSubmissionWindow;
import page.MainPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormTests extends BaseTest {

    @Test
    void submissionModalFormTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open(TECHSHOP_URL)
            .getHeader()
            .clickBtnRequestCall()
            .fillForm("Test", "331111111", "Test")
            .submitForm();

        SuccessSubmissionWindow successSubmissionForm = new SuccessSubmissionWindow(getDriver());

        boolean successMessageDisplayed = successSubmissionForm.isSuccessMessageDisplayed();
        Assertions.assertTrue(successMessageDisplayed, "Successful submission message is not displayed");
    }

    // @Test
    // void checkRequiredFieldsTest() {
    //     SuccessSubmissionWindow RequestCallModalForm = new MainPage(getDriver())
    //         .open(TECHSHOP_URL)
    //         .getHeader()
    //         .clickBtnRequestCall()
    //         .submitForm();
    //
    //     SuccessSubmissionWindow SuccessSubmissionWindow = new SuccessSubmissionWindow(getDriver());
    //
    //     boolean isNameFieldRequired = SuccessSubmissionWindow.isNameFieldRequired();
    //     assertTrue(isNameFieldRequired);
    //
    //     boolean isPhoneFieldRequired = SuccessSubmissionWindow.isPhoneFieldRequired();
    //     assertTrue(isPhoneFieldRequired);
    // }
}
