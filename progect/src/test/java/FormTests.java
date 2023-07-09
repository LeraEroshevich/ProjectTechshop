import static page.MainPage.TECHSHOP_URL;

import compinents.RequestCallModalForm;
import page.MainPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormTests extends BaseTest {

    @Test
    void submissionModalFormTest() {
        RequestCallModalForm modalForm = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getHeader()
            .clickBtnRequestCall()
            .fillName("Test")
            .fillPhone("331111111")
            .fillMessage("Test")
            .submitForm();
        boolean successMessageDisplayed = modalForm.isSuccessMessageDisplayed();
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
        Assertions.assertTrue(isNameFieldRequired);

        boolean isPhoneFieldRequired = modalForm.isPhoneFieldRequired();
        Assertions.assertTrue(isPhoneFieldRequired);
    }
}
