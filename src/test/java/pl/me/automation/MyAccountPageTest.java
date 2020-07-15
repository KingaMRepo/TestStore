/*package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.EditAddressPage;
import pl.me.automation.page.HomePage;
import pl.me.automation.page.MyAccountPage;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class MyAccountPageTest {
    private WebDriver webDriver;
    private HomePage homePage;

    @BeforeEach
    public void init(){
        webDriver = WebDriverType.CHROME.create();
        webDriver.get("https://www.storetestproject.hekko24.pl/");
        homePage = new HomePage(webDriver);
        homePage.clickCookie();
    }

    @AfterEach
    public  void destroy(){
        //webDriver.close();
    }

    @Test
    public void shouldRegisterUser(){
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterUserName("qwer"+now.toString().replaceAll(":","-"));
        myAccountPage.enterRegisterUserEmail("qwer"+now.toString().replaceAll(":","-")+"@onet.pl");
        myAccountPage.enterRegisterUserPassword("Qqwer123!@#&*");
        myAccountPage.clickRegister();
        assertThat(myAccountPage.isRegistrationError()).isFalse();
        //myAccountPage.enterLoginUserNameOrEmail("qwer"+now.toString().replaceAll(":","-")+"@onet.pl");
        //myAccountPage.enterLoginUserLoginPassword("Qqwer123!@#&*");
        //myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getDisplayedEmail()).contains("qwer"+now.toString().replaceAll(":","-"));
        EditAddressPage editAddressPage = myAccountPage.clickAddressAndBillingButton().clickAddressAndBillingEditButton();
        editAddressPage.enterBillingUserName("Anna");
        editAddressPage.enterBillingUserLastName("Kwiatkowska");
        editAddressPage.selectBillingCountryName("Albania");


    }









}
*/