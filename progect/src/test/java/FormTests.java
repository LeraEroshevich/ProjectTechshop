import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import compinents.RequestCallModalForm;
import compinents.SuccessSubmissionForm;
import page.MainPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormTests extends BaseTest {

    @Test
    void submissionModalFormTest() {
        RequestCallModalForm successSubmissionForm = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .clickBtnRequestCall()
            .fillForm("Test", "331111111", "Test")
            .submitForm();

        SuccessSubmissionForm mainPage = new SuccessSubmissionForm(getDriver());
        boolean successMessageDisplayed = mainPage.isSuccessMessageDisplayed();
        Assertions.assertTrue(successMessageDisplayed, "Successful submission message is not displayed");
    }

    @Test
    void checkRequiredFieldsTest() {
        RequestCallModalForm modalForm = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .clickBtnRequestCall()
            .submitForm();

        boolean isNameFieldRequired = modalForm.isNameFieldRequired();
        assertTrue(isNameFieldRequired);

        boolean isPhoneFieldRequired = modalForm.isPhoneFieldRequired();
        assertTrue(isPhoneFieldRequired);
    }
}
