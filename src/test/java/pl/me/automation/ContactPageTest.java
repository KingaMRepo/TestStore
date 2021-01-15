package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.ContactPage;
import pl.me.automation.page.HomePage;
import pl.me.automation.utils.Contact;
import pl.me.automation.utils.TestDataReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPageTest extends TestDataReader {
    private WebDriver webDriver;
    private HomePage homePage;


    @BeforeEach
    public void init() {
        webDriver = WebDriverType.CHROME.create();
        webDriver.get("https://www.storetestproject.hekko24.pl/");
        homePage = new HomePage(webDriver);
        homePage.clickCookie();
    }

    @AfterEach
    public void destroy() {
        webDriver.close();
    }

    @Test
    public void shouldFillInAndSendForm() {
        ContactPage contactPage = homePage.clickContact();
        contactPage.enterUserName(contact.getContactPageEnterUserName());
        contactPage.enterUserLastName(contact.getContactPageEnterUserLastName());
        contactPage.selectMessageSubject(contact.getContactPageSelectMessageSubject());
        contactPage.enterEmailAddress(contact.getContactPageEnterEmailAddress());
        contactPage.enterMessage(contact.getContactPageSelectMessageSubject());
        contactPage.clickSendMessageButton();
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
        contactPage.enterUserName(contact.getContactPageEnterUserName());
        contactPage.enterUserLastName(contact.getContactPageEnterUserLastName());
        contactPage.selectMessageSubject(contact.getContactPageSelectMessageSubject());
        contactPage.enterEmailAddress(contact.getContactPageEnterIncorrectEmailAddress());
        contactPage.enterMessage(contact.getContactPageEnterMessage());
        contactPage.clickSendMessageButton();
        assertTrue(contactPage.isEmailValidationErrorDisplayed());
    }

}