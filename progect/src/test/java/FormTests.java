import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.MainPage.TECHSHOP_URL;

import compinents.RequestCallModalForm;
import compinents.SuccessSubmissionWindow;
import page.MainPage;
import page.PCSelectionPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormTests extends BaseTest {

    @Test
    void submissionModalFormTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.open(TECHSHOP_URL).getHeader().clickBtnRequestCall().fillForm("Test", "331111111", "Test").submitForm();

        SuccessSubmissionWindow successSubmissionForm = new SuccessSubmissionWindow(getDriver());

        boolean successMessageDisplayed = successSubmissionForm.isSuccessMessageDisplayed();
        Assertions.assertTrue(successMessageDisplayed, "Successful submission message is not displayed");
    }

    @Test
    void checkRequiredFieldsTest() {
        MainPage mainPage = new MainPage(getDriver());
        RequestCallModalForm requestCallForm = mainPage.open(TECHSHOP_URL)
            .getHeader()
            .clickBtnRequestCall();
            requestCallForm.submitForm();

        boolean isNameFieldRequired = requestCallForm.isNameFieldRequired();
        assertTrue(isNameFieldRequired, "Name field should be required");

        boolean isPhoneFieldRequired = requestCallForm.isPhoneFieldRequired();
        assertTrue(isPhoneFieldRequired, "Phone field should be required");
    }

    @Test
    void testRequiredRadioButtonOnFirstStep() {

        PCSelectionPage pcSelectionPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getPCSelectionForm()
            .clickGoToSelectionBtn();

        String initialStepTitle = pcSelectionPage.getCurrentStepTitle();
        pcSelectionPage.clickGoToNextStepBtn(0);
        assertTrue(pcSelectionPage.isCurrentStepTitle(initialStepTitle));
        pcSelectionPage.clickRadioButtonByText("Для работы (офисные программы, интернет)")
            .clickGoToNextStepBtn(0);

        assertFalse(pcSelectionPage.isCurrentStepTitle(initialStepTitle));
    }

@Test
    void pcSelectionFormTest() {
        PCSelectionPage pcSelectionPage = new MainPage(getDriver())
            .open(TECHSHOP_URL)
            .getPCSelectionForm()
            .clickGoToSelectionBtn()
            .fillFormAndSubmit()
            .fillNameAndPhoneFields("Test", "111111111")
            .clickBtnSend();

        pcSelectionPage.assertConfirmationMessageDisplayed("Благодарим Вас за заявку.");
    }
}
