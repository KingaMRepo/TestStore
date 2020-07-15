package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.ContactPage;
import pl.me.automation.page.HomePage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactPageTest extends MyProperties {
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
        contactPage.enterUserName("Dale");
        contactPage.enterUserLastName("Cooper");
        contactPage.selectMessageSubject("pytanie o produkt");
        contactPage.enterEmailAddress("rirobjim679@lefaqr5.com");
        contactPage.enterMessage("Neque porro quisquam est qui dolorem ipsum quia");
        contactPage.clickSendMessageButton();
        assertTrue(contactPage.isContactMessageDisplayed());
    }

    @Test
    public void shouldFillInAndSendFormWithLongMessage() {
        ContactPage contactPage = homePage.clickContact();
        contactPage.enterUserName("Dale");
        contactPage.enterUserLastName("Cooper");
        contactPage.selectMessageSubject("pytanie o produkt");
        contactPage.enterEmailAddress("rirobim664@lefaqr5.com");
        contactPage.enterMessage((String) properties.get("message"));
        contactPage.clickSendMessageButton();
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
        contactPage.enterUserName("Shelly");
        contactPage.enterUserLastName("Johnson");
        contactPage.selectMessageSubject("reklamacja");
        contactPage.enterEmailAddress("rirobim664lefaqr5.com");
        contactPage.enterMessage("Lorem ipsum dolor sit amet, consectetur adipiscing el");
        contactPage.clickSendMessageButton();
        assertThat(contactPage.isEmailValidationErrorDisplayed()).isTrue();
    }

}
