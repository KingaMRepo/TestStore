package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MyAccountPageTest extends MyAccountLogInForm {
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
        //webDriver.close();
    }

    //Registration tests
    @Test
    public void shouldCorrectlyRegisterUser() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("Wert" + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail("Wert" + now.toString().replaceAll(":", "-") + "@onet.pl");
        myAccountPage.enterRegisterUserPassword("Qwer123!@#&*");
        myAccountPage.clickRegister();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isFalse();
    }

    @Test
    public void shouldIncorrectlyRegisterUserName() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("");
        myAccountPage.enterRegisterUserEmail("Wert" + now.toString().replaceAll(":", "-") + "@onet.pl");
        myAccountPage.enterRegisterUserPassword("Qwer123!@#&*");
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldRegisterUserWithIncorrectEmail() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("Wert" + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail("");
        myAccountPage.enterRegisterUserPassword("Qwer123!@#&*");
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldIncorrectlyRegisterUserPassword() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("wert");
        myAccountPage.enterRegisterUserEmail("Wert" + now.toString().replaceAll(":", "-") + "@onet.pl");
        myAccountPage.enterRegisterUserPassword("");
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }
    //Login tests
    @Test
    public void shouldLoginWithEmptyRegistrationForm() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldCorrectlyLoginAsUser() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginForm(myAccountPage);
        assertThat(myAccountPage.getUserNameText().contains("user99"));
    }

    @Test
    public void shouldLoginWithIncorrectUserNameOrEmail() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user00@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&Lgqb");
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldLoginWithIncorrectUserPassword() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&");
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldLoginWithEmptyLoginForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldLoginWithLostPasswordReminder() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&");
        myAccountPage.clickLoginSubmit();
        LostPasswordReminderPage lostPasswordReminderPage = myAccountPage.clickLostPasswordReminderButton();
        lostPasswordReminderPage.insertUserLoginOrEmail("user555");
        lostPasswordReminderPage.clickResetPasswordButton();
        assertThat(lostPasswordReminderPage.getResetPasswordText()).isEqualTo(lostPasswordReminderPage.resetText);
    }

    @Test
    public void shouldCorrectlyLoginAsUserWithRememberMeCheckbox() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginFormWitRememberMeCheckbox(myAccountPage);
        assertThat(myAccountPage.isUserNameTextDisplayed()).isTrue();
    }

    @Test
    public void shouldCorrectlyLoginAsUserAndBackToHomePage() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginForm(myAccountPage);
        String userName = myAccountPage.getUserNameText();
        myAccountPage.clickHome();
        myAccountPage.clickMyAccount();
        String userNameBackspace = myAccountPage.getUserNameText();
        assertThat(userName).isEqualTo(userNameBackspace);
    }

//Displaying customer information
    @Test
    public void shouldGoFromOrderToOrdersList() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Black Jacket");
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        LocalDateTime now = LocalDateTime.now();
        paymentPage.enterBillingUserName("rafałł");
        paymentPage.enterBillingUserLastName("kwiatkowski");
        paymentPage.selectBillingCountry(1);
        paymentPage.enterBillingUserAddress("Kwiatowa");
        paymentPage.enterBillingUserCity("Poznań");
        paymentPage.enterBillingUserPostCode("60001");
        paymentPage.enterBillingUserPhone("600500400");
        paymentPage.enterBillingUserEmail("qwert!#11" + now.toString().replaceAll(":", "-") + "@nazwa.pl");
        paymentPage.enterBillingAccountUsername("zxcvbn" + UUID.randomUUID().toString());
        paymentPage.enterBillingAccountPassword("&%SIytrdf!90" + now.toString().replaceAll(":", "-"));
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        String orderNumber = orderPage.getOrderNumber();
        MyAccountPage myAccountPage = paymentPage.clickMyAccount();
        myAccountPage.goToOrdersList();
        String lastOrder = myAccountPage.getOrderListLastOrder();
        assertThat(orderNumber).isEqualTo(lastOrder);
    }
//Edit billing address
    @Test
    public void shouldEditAndCorrectlyFillInBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&Lgqb");
        myAccountPage.clickLoginSubmit();
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        editAddressPage.enterBillingUserName("Aglaea");
        editAddressPage.enterBillingUserLastName("Preethi");
        editAddressPage.selectBillingCountryName("Albania");
        editAddressPage.enterBillingAddress1("Sun Alley");
        editAddressPage.enterBillingPostCode("30404");
        editAddressPage.enterBillingCity("Wlora");
        editAddressPage.enterBillingRegion("Delvina");
        editAddressPage.enterBillingPhoneNumber("111222333");
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.isEditBillingAddressAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldEditAndIncorrectlyFillInBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&Lgqb");
        myAccountPage.clickLoginSubmit();
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        editAddressPage.enterBillingUserName("");
        editAddressPage.enterBillingUserLastName("Preethi");
        editAddressPage.selectBillingCountryName("Albania");
        editAddressPage.enterBillingAddress1("Sun Alley");
        editAddressPage.enterBillingPostCode("");
        editAddressPage.enterBillingCity("Wlora");
        editAddressPage.enterBillingRegion("Delvina");
        editAddressPage.enterBillingPhoneNumber("$!");
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.getErrorLabels()).containsExactly("Imię jest wymaganym polem.", "Kod pocztowy jest wymaganym polem.", "Telefon nie jest poprawnym numerem telefonu.");
    }

    @Test
    public void shouldEditAndSendEmptyBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&Lgqb");
        myAccountPage.clickLoginSubmit();
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        editAddressPage.clearBillingUserName();
        editAddressPage.clearBillingUserLastName();
        editAddressPage.clearBillingAddress1();
        editAddressPage.clearBillingCity();
        editAddressPage.clearBillingRegion();
        editAddressPage.clearBillingPostCode();
        editAddressPage.clearBillingPhoneNumber();
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.getErrorLabels()).containsExactly("Imię jest wymaganym polem.", "Nazwisko jest wymaganym polem.", "Ulica jest wymaganym polem.", "Miasto jest wymaganym polem.", "Województwo / Region jest wymaganym polem.", "Kod pocztowy jest wymaganym polem.", "Telefon jest wymaganym polem.");
    }
//Test nie przechodzi ponieważ button jest hidden
    @Test
    public void shouldCorrectlyFillInChangePasswordAndAccountDetailsForm(){
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginForm(myAccountPage);
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.enterAccountFirstName("aaa");
        changePasswordPage.enterAccountLastName("bbb");
        changePasswordPage.enterAccountDisplayName("aabb");
        changePasswordPage.enterAccountEmail("user99@nazwa.pl");
        changePasswordPage.enterCurrentPassword("9[}JX7v]Y1m5&Lgqb");
        changePasswordPage.enterNewPassword("9[}JX7v]Y1m5&$$");
        changePasswordPage.acceptNewPassword("9[}JX7v]Y1m5&$$");
        changePasswordPage.submitPassword();
    }

    @Test
    public void shouldGoFromOrderListAndCheckIfPaymentDetailsAreEqualsToMyAccountPaymentDetails() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Black Jacket");
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        LocalDateTime now = LocalDateTime.now();
        paymentPage.enterBillingUserName("rafałł");
        paymentPage.enterBillingUserLastName("kwiatkowski");
        paymentPage.selectBillingCountry(1);
        paymentPage.enterBillingUserAddress("Kwiatowa");
        paymentPage.enterBillingUserCity("Poznań");
        paymentPage.enterBillingUserPostCode("60001");
        paymentPage.enterBillingUserPhone("600500400");
        paymentPage.enterBillingUserEmail("qwert!#11" + now.toString().replaceAll(":", "-") + "@nazwa.pl");
        paymentPage.enterBillingAccountUsername("zxcvbn" + UUID.randomUUID().toString());
        paymentPage.enterBillingAccountPassword("&%SIytrdf!90" + now.toString().replaceAll(":", "-"));
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        String orderPaymentDetails = orderPage.getOrderPaymentDetails();
        MyAccountPage myAccountPage = paymentPage.clickMyAccountWaitForOrder();
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        String paymentAddress = myAccountPage.getPaymentAddress();
        //myAccountPage.clickMyAccount();
        //myAccountPage.goToOrdersList();
        //myAccountPage.clickOrderDetailsButton(0);
        //String paymentAddress = myAccountPage.getPaymentAddress();

    }


}




