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
        webDriver.close();
    }

    @Test
    public void shouldCorrectlyRegisterUser() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-") + myAccount.getMyAccountPageEnterRegisterUserEmail());
        myAccountPage.enterRegisterUserPassword(myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickRegister();
        assertTrue(myAccountPage.isWelcomeToMyAccountTextDisplay());
    }


    @Test
    public void shouldRegisterUserWithEmptyEmail() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail(myAccount.getMyAccountPageEnterEmptyField());
        myAccountPage.enterRegisterUserPassword(myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorEmailText()));
    }


    @Test
    public void shouldRegisterUserWithEmptyName() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName(myAccount.getMyAccountPageEnterEmptyField());
        myAccountPage.enterRegisterUserEmail(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-") + myAccount.getMyAccountPageEnterRegisterUserEmail());
        myAccountPage.enterRegisterUserPassword(myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginUserErrorText()));
    }

    @Test
    public void shouldRegisterUserWithEmptyPassword() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"));
        myAccountPage.enterRegisterUserEmail(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-") + myAccount.getMyAccountPageEnterRegisterUserEmail());
        myAccountPage.enterRegisterUserPassword(myAccount.getMyAccountPageEnterEmptyField());
        myAccountPage.clickRegisterFalse();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorPasswordText()));
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
        assertThat(myAccountPage.getUserNameText().contains(myAccount.getMyAccountPageGetUserNameText()));
    }


    @Test
    public void shouldLoginWithIncorrectUserName() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterRegisterUserName(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"));
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-") + myAccount.getMyAccountPageEnterRegisterUserEmail());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorText()));
    }

    @Test
    public void shouldLoginWithIncorrectUserPassword() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageEnterRegisterUserEmail());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageEnterLoginUserLoginIncorrectPassword());
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorText()));
    }


    @Test
    public void shouldLoginWithIncorrectUserEmail() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageEnterIncorrectLoginUserNameOrEmail());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorText()));
    }


    @Test
    public void shouldLoginWithEmptyLoginForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().contains(myAccount.getMyAccountPageGetRegistrationAndLoginRequiredUserErrorText()));
    }

    @Test
    public void shouldLoginWithEmptyUserNameOrEmail() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageEnterEmptyField());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginRequiredUserErrorText()));

    }

    @Test
    public void shouldLoginWithEmptyUserPassword() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageEnterRegisterUser());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageEnterEmptyField());
        myAccountPage.clickLoginSubmit();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorText()));
    }

    @Test
    public void shouldLoginWithLostPasswordReminder() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        LostPasswordReminderPage lostPasswordReminderPage = myAccountPage.clickLostPasswordReminderButton();
        lostPasswordReminderPage.insertUserLoginOrEmail(myAccount.getChangePasswordPageEnterAccountEmail());
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
    public void shouldGoFromOrderToOrdersListAndCheckIfOrderNumberIsOnTheList() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket1());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        fillInPaymentFormCorrectly(paymentPage);
        paymentPage.deselectShipToDifferentAddressCheckbox();
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
        assertThat(editAddressPage.getErrorsLabelsStrong()).containsExactly(myAccount.getMyAccountPageGetErrorLabels());
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
        assertThat(editAddressPage.getErrorsLabels()).containsExactly(myAccount.getMyAccountPageGetBillingErrorLabels());
    }

    @Test
    public void shouldEditAndCorrectlyFillInShippingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditShippingAddressPage editShippingAddress = myAccountPage.clickEditShippingAddressEditButtonButton();
        changeCorrectlyShippingAddress(editShippingAddress);
        editShippingAddress.clickShippingSubmitButton();
        assertThat(editShippingAddress.getShippingAddressFormAlert().contains(myAccount.getEditShippingAddressGetShippingAddressFormAlert()));
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
        assertThat(editShippingAddress.getErrorLabels()).contains(myAccount.getMyAccountPageGetShippingErrorLabels());
    }

    @Test
    public void shouldCorrectlyFillInChangePasswordAndAccountDetailsForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsUserForm(myAccountPage);
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.enterAccountFirstName(myAccount.getMyAccountPageGetUserNameText());
        changePasswordPage.enterAccountLastName(myAccount.getMyAccountPageGetUserNameText());
        changePasswordPage.enterAccountDisplayName(myAccount.getMyAccountPageGetUserNameText());
        changePasswordPage.enterAccountEmail(myAccount.getChangePasswordPageEnterAccountEmail());
        changePasswordPage.enterCurrentPassword(myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.enterNewPassword(myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.acceptNewPassword(myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.submitPassword();
        assertTrue(changePasswordPage.isAccountDetailsChangedAlertAlertDisplayed());

    }

    @Test
    public void shouldIncorrectlyFillInChangePasswordAndAccountDetailsForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsUserForm(myAccountPage);
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.enterAccountFirstName(myAccount.getMyAccountPageGetUserNameText());
        changePasswordPage.enterAccountLastName(myAccount.getMyAccountPageGetUserNameText());
        changePasswordPage.enterAccountDisplayName(myAccount.getMyAccountPageGetUserNameText());
        changePasswordPage.enterAccountEmail(myAccount.getMyAccountPageEnterIncorrectLoginUserNameOrEmail());
        changePasswordPage.enterAccountDisplayName(myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.enterCurrentPassword(myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.enterNewPassword(myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.acceptNewPassword(myAccount.getMyAccountPageEnterEmptyField());
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
        assertThat(changePasswordPage.getErrorLabels()).containsExactly(myAccount.getMyAccountPageGetErrorLabels());

    }

}




