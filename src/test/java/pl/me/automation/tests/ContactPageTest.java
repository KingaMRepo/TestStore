package pl.me.automation.tests;

import org.junit.jupiter.api.*;
import pl.me.automation.pages.ContactPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.*;

@TestInstance(Lifecycle.PER_CLASS)
public class ContactPageTest extends BaseTest {

    @Test
    public void shouldFillInAndSendForm() {
        ContactPage contactPage = homePage.clickContact();
        contactPage.fillInContactForm(contact.getContactPageEnterUserName(),
        contact.getContactPageEnterUserLastName(),
        contact.getContactPageSelectMessageSubject(),
        contact.getContactPageEnterEmailAddress(),
        contact.getContactPageSelectMessageSubject());
        assertTrue(contactPage.isContactMessageDisplayed());
    }

    @Test
    public void shouldSendEmptyForm() {
        ContactPage contactPage = homePage.clickContact();
        contactPage.clickSendMessageButton();
        assertTrue(contactPage.isEmailValidationErrorDisplayed());
        assertTrue(contactPage.isNameValidationError());
        assertTrue(contactPage.isMessageValidationError());
    }

    @Test
    public void shouldSendFormWithIncorrectEmail() {
        ContactPage contactPage = homePage.clickContact();
        contactPage.fillInContactForm(contact.getContactPageEnterUserName(),
        contact.getContactPageEnterUserLastName(),
        contact.getContactPageSelectMessageSubject(),
        contact.getContactPageEnterIncorrectEmailAddress(),
        contact.getContactPageEnterMessage());
        assertTrue(contactPage.isEmailValidationErrorDisplayed());
    }



}