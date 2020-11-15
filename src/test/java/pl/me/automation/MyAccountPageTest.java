package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyAccountPageTest extends Forms {
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

    @Test
    public void shouldCorrectlyRegisterUser() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("Wert" + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail("Wert" + now.toString().replaceAll(":", "-") + "@onet.pl");
        myAccountPage.enterRegisterUserPassword("Qwer123!@#&*");
        myAccountPage.clickRegister();
        assertTrue(myAccountPage.isWelcomeToMyAccountTextDisplay());
    }


    @Test
    public void shouldRegisterUserWithEmptyEmail() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("Wert" + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail("");
        myAccountPage.enterRegisterUserPassword("Qwer123!@#&*");
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Podaj poprawny adres e-mail."));
    }


    @Test
    public void shouldRegisterUserWithEmptyName() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("");
        myAccountPage.enterRegisterUserEmail("Wert" + now.toString().replaceAll(":", "-") + "@onet.pl");
        myAccountPage.enterRegisterUserPassword("Qwer123!@#&*");
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Proszę wpisać prawidłową nazwę użytkownika."));
    }

    @Test
    public void shouldRegisterUserWithEmptyPassword() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("wert" + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail("Wert" + now.toString().replaceAll(":", "-") + "@onet.pl");
        myAccountPage.enterRegisterUserPassword("");
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Proszę wpisać hasło."));
    }

    @Test
    public void shouldLoginWithEmptyRegistrationForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldCorrectlyLoginAsUser() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        assertThat(myAccountPage.getUserNameText().contains("admin"));
    }


    @Test
    public void shouldLoginWithIncorrectUserName() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName("1&$" + now.toString().replaceAll(":", "-"));
        myAccountPage.enterLoginUserNameOrEmail("Wert" + now.toString().replaceAll(":", "-") + "@onet.pl");
        myAccountPage.enterLoginUserLoginPassword("x6Z7Vr%zh3N?HvD");
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Incorrect username or password"));
    }

    @Test
    public void shouldLoginWithIncorrectUserPassword() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user134@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9/[}JX7v]Y1m5&");
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Incorrect username or password"));
    }


    @Test
    public void shouldLoginWithIncorrectUserEmail() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("#@%^%#$@#$@#.com");
        myAccountPage.enterLoginUserLoginPassword("x6Z7Vr%zh3N?HvD");
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Incorrect username or password"));
    }


    @Test
    public void shouldLoginWithEmptyLoginForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().contains("Nazwa użytkownika jest wymagana."));
    }

    @Test
    public void shouldLoginWithEmptyUserNameOrEmail() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail(" ");
        myAccountPage.enterLoginUserLoginPassword("x6Z7Vr%zh3N?HvD");
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Nazwa użytkownika jest wymagana."));

    }


    @Test
    public void shouldLoginWithEmptyUserPassword() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("Test01@nazwa.com ");
        myAccountPage.enterLoginUserLoginPassword(" ");
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals("Incorrect username or password"));
    }

    @Test
    public void shouldLoginWithLostPasswordReminder() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail("user99@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("9[}JX7v]Y1m5&");
        myAccountPage.clickLoginSubmit();
        LostPasswordReminderPage lostPasswordReminderPage = myAccountPage.clickLostPasswordReminderButton();
        lostPasswordReminderPage.insertUserLoginOrEmail("Test01@nazwa.com");
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
        fillLoginAsAdminForm(myAccountPage);
        String userName = myAccountPage.getUserNameText();
        myAccountPage.clickHome();
        myAccountPage.clickMyAccount();
        String userNameBackspace = myAccountPage.getUserNameText();
        assertThat(userName).isEqualTo(userNameBackspace);
    }

    @Test
    public void shouldGoFromOrderToOrdersListAndCheckIfOrderNumberIsOnTheList() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Black Jacket");
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        fillInPaymentForm(paymentPage);
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        String orderNumber = orderPage.getOrderNumber();
        MyAccountPage myAccountPage = paymentPage.clickMyAccount();
        myAccountPage.clickLastOrdersButton();
        String lastOrder = myAccountPage.getOrderListLastOrder();
        assertThat(orderNumber).isEqualTo(lastOrder);
    }


    @Test
    public void shouldEditAndCorrectlyFillInBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditBillingAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        changeCorrectlyBillingAddress(editAddressPage);
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.isEditBillingAddressAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldEditAndIncorrectlyFillInBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditBillingAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        changeIncorrectlyBillingAddress(editAddressPage);
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.getErrorLabels()).containsExactly("Imię jest wymaganym polem.", "Kod pocztowy jest wymaganym polem.", "Telefon nie jest poprawnym numerem telefonu.");
    }

    @Test
    public void shouldEditAndSendEmptyBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditBillingAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
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

    @Test
    public void shouldEditAndCorrectlyFillInShippingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditShippingAddressPage editShippingAddress = myAccountPage.clickEditShippingAddressEditButtonButton();
        changeCorrectlyShippingAddress(editShippingAddress);
        editShippingAddress.clickShippingSubmitButton();
        assertThat(editShippingAddress.getShippingAddressFormAlert().contains("Adres został zmieniony."));
    }


    @Test
    public void shouldEditAndIncorrectlyFillInShippingAddress() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditShippingAddressPage editShippingAddress = myAccountPage.clickEditShippingAddressEditButtonButton();
        changeIncorrectlyShippingAddress(editShippingAddress);
        editShippingAddress.clickShippingSubmitButton();
        assertTrue(editShippingAddress.isShippingAddressFormAlert());

    }

    @Test
    public void shouldEditAndSendEmptyFillInShippingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditShippingAddressPage editShippingAddress = myAccountPage.clickEditShippingAddressEditButtonButton();
        editShippingAddress.clearShippingUserName();
        editShippingAddress.clearShippingUserLastName();
        editShippingAddress.clearShippingAddress1();
        editShippingAddress.clearShippingPostcode();
        editShippingAddress.clearShippingCity();
        editShippingAddress.clickShippingSubmitButton();
        assertThat(editShippingAddress.getErrorLabels()).containsExactly("Imię jest wymaganym polem.", "Nazwisko jest wymaganym polem.", "Ulica jest wymaganym polem.","Kod pocztowy jest wymaganym polem.","Miasto jest wymaganym polem.");

    }

    @Test
    public void shouldCorrectlyFillInChangePasswordAndAccountDetailsForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsUserForm(myAccountPage);
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.enterAccountFirstName("admin");
        changePasswordPage.enterAccountLastName("admin");
        changePasswordPage.enterAccountDisplayName("aaadmin");
        changePasswordPage.enterAccountEmail("nazwa@nazwa.pl");
        changePasswordPage.enterCurrentPassword("");
        changePasswordPage.enterNewPassword("");
        changePasswordPage.acceptNewPassword("");
        changePasswordPage.submitPassword();

    }

    @Test
    public void shouldIncorrectlyFillInChangePasswordAndAccountDetailsForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsUserForm(myAccountPage);
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.enterAccountFirstName("admin");
        changePasswordPage.enterAccountLastName("admin");
        changePasswordPage.enterAccountDisplayName("aaadmin");
        changePasswordPage.enterAccountEmail("nazwanazwa.pl");
        changePasswordPage.enterAccountDisplayName("");
        changePasswordPage.enterCurrentPassword("");
        changePasswordPage.enterNewPassword("");
        changePasswordPage.acceptNewPassword("");
        changePasswordPage.submitPassword();
        assertTrue(changePasswordPage.isMyAccountWelcomeTextDisplayed());

    }

    @Test
    public void shouldSendEmptyFillInChangePasswordAndAccountDetailsForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsUserForm(myAccountPage);
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.clearAccountFirstName();
        changePasswordPage.clearAccountLastName();
        changePasswordPage.clearAccountDisplayName();
        changePasswordPage.clearAccountEmail();
        changePasswordPage.submitPassword();
        assertThat(changePasswordPage.getErrorLabels()).containsExactly("Imię jest wymaganym polem.", "Nazwisko jest wymaganym polem.", "Nazwa wyświetlana jest wymaganym polem.", "Adres email jest wymaganym polem.");

    }



}




