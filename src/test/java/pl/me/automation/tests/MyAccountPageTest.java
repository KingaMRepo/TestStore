package pl.me.automation.tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.pages.*;


import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyAccountPageTest extends BaseTest {


    @Test
    public void shouldCorrectlyRegisterUser() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInMyAccountRegistration(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"),
        myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-") + myAccount.getMyAccountPageEnterRegisterUserEmail(),
        myAccount.getMyAccountPageEnterRegisterUserPassword());
        assertTrue(myAccountPage.isWelcomeToMyAccountTextDisplay());
    }


    @Test
    public void shouldRegisterUserWithEmptyEmail() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInMyAccountRegistrationFail(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"),
        myAccount.getMyAccountPageEnterEmptyField(), myAccount.getMyAccountPageEnterRegisterUserPassword());
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorEmailText()));
    }


    @Test
    public void shouldRegisterUserWithEmptyName() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInMyAccountRegistrationFail(myAccount.getMyAccountPageEnterEmptyField(),
        myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-") + myAccount.getMyAccountPageEnterRegisterUserEmail(),
        myAccount.getMyAccountPageEnterRegisterUserPassword());
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginUserErrorText()));
    }

    @Test
    public void shouldRegisterUserWithEmptyPassword() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInMyAccountRegistrationFail(
        myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"),
        myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-") + myAccount.getMyAccountPageEnterRegisterUserEmail(),
        myAccount.getMyAccountPageEnterEmptyField());
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorPasswordText()));
    }

    @Test
    public void shouldLoginWithEmptyRegistrationForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickRegistrationButton();
        assertThat(myAccountPage.isRegistrationAndLoginErrorDisplayed()).isTrue();
    }

    @Test
    public void shouldCorrectlyLoginAsUser() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
        myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        assertThat(myAccountPage.getUserNameText().contains(myAccount.getMyAccountPageGetUserNameText()));
    }


    @Test
    public void shouldLoginWithIncorrectUserName() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterRegisterUser() + now.toString().replaceAll(":", "-"),
        myAccount.getMyAccountPageEnterRegisterUserPassword());
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorText()));
    }

    @Test
    public void shouldLoginWithIncorrectUserPassword() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterRegisterUserEmail(),
        myAccount.getMyAccountPageEnterLoginUserLoginIncorrectPassword());
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorText()));
    }


    @Test
    public void shouldLoginWithIncorrectUserEmail() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterIncorrectLoginUserNameOrEmail(),
        myAccount.getMyAccountPageEnterRegisterUserPassword());
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginErrorText()));
    }


    @Test
    public void shouldLoginWithEmptyLoginForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickLoginButton();
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().contains(myAccount.getMyAccountPageGetRegistrationAndLoginRequiredUserErrorText()));
    }


    @Test
    public void shouldLoginWithEmptyUserNameOrEmail() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterEmptyField(),
        myAccount.getMyAccountPageEnterRegisterUserPassword());
        assertThat(myAccountPage.getRegistrationAndLoginErrorText().equals(myAccount.getMyAccountPageGetRegistrationAndLoginRequiredUserErrorText()));

    }

    @Test
    public void shouldLoginWithEmptyUserPassword() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterRegisterUser(),
        myAccount.getMyAccountPageEnterEmptyField());
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
        myAccountPage.fillLoginFormWithRememberMeCheckbox(myAccount.getMyAccountPageRememberMeCheckboxEnterEmail(),
        myAccount.getMyAccountPageRememberMeCheckboxEnterPassword());
        assertThat(myAccountPage.isUserNameTextDisplayed()).isTrue();
    }


    @Test
    public void shouldGoFromOrderToOrdersListAndCheckIfOrderNumberIsOnTheList() {
        LocalDateTime now = LocalDateTime.now();
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket1());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.fillInPaymentForm(
                payment.getPaymentPageEnterBillingUserName() + now.toString().replaceAll(":", "-"),
                payment.getPaymentPageEnterBillingUserLastName(),
                payment.getPaymentPageSelectBillingCountry(),
                payment.getPaymentPageEnterBillingUserAddress(),
                payment.getBillingCity(),
                payment.getPaymentPageEnterProvince(),
                payment.getPaymentPageEnterBillingUserPostCode(),
                payment.getPaymentPageEnterBillingUserPhone(),
                payment.getPaymentPageEnterBillingAccountUsername() + now.toString().replaceAll(":", "-") + payment.getPaymentPageEnterBillingUserEmailDomain(),
                payment.getPaymentPageEnterBillingAccountUsername() + UUID.randomUUID().toString(),
                payment.getPaymentPageEnterUserPassword() + now.toString().replaceAll(":", "-"));
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
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditBillingAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        editAddressPage.fillInBillingAddressForm(myAccount.getEditAddressPageEnterBillingUserLastName(),
        myAccount.getEditAddressPageEnterBillingUserName(),
        myAccount.getEditAddressPageSelectBillingCountryName(),
        myAccount.getEditAddressPageEnterBillingAddress1(),
        myAccount.getEditAddressPageEnterBillingPostCode(),
        myAccount.getEditAddressPageEnterBillingCity(),
        myAccount.getEditAddressPageEnterBillingRegion(),
        myAccount.getEditAddressPageEnterBillingPhoneNumber());
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.isEditBillingAddressAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldEditAndIncorrectlyFillInBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterRegisterUser(),
                myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditBillingAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        editAddressPage.fillInBillingAddressForm(myAccount.getEditAddressPageEnterBillingUserLastName(),
                myAccount.getEditAddressPageEnterBillingUserName(),
                myAccount.getEditAddressPageSelectBillingCountryName(),
                myAccount.getEditAddressPageEnterBillingAddress1(),
                myAccount.getEditAddressPageEnterBillingPostCode(),
                myAccount.getEditAddressPageEnterBillingCity(),
                myAccount.getEditAddressPageEnterBillingRegion(),
                myAccount.getEditAddressPageEnterBillingPhoneNumber());
        editAddressPage.clickBillingSubmitButton();
        editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        editAddressPage.fillInBillingAddressForm(myAccount.getEditAddressPageEnterBillingUserLastName(),
                myAccount.getEditAddressPageEnterBillingUserName(),
                myAccount.getEditAddressPageSelectBillingCountryName(),
                myAccount.getEditAddressPageEnterBillingAddress1(),
                myAccount.getEditAddressPageEnterBillingCity(),
                myAccount.getEditAddressPageEnterBillingRegion(),
                myAccount.getEditAddressPageEnterBillingPostCode(),
                myAccount.getEditAddressPageEnterIncorrectBillingPhoneNumber());;
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.getErrorsLabelsStrong()).containsExactly(myAccount.getMyAccountPageGetBillingFormErrorLabels());
    }

    @Test
    public void shouldEditAndSendEmptyBillingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterRegisterUser(),
                myAccount.getMyAccountPageEnterRegisterUserPassword());
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditBillingAddressPage editAddressPage = myAccountPage.clickEditAddressAndBillingEditButton();
        editAddressPage.clearBillingAddressFormFields();
        editAddressPage.clickBillingSubmitButton();
        assertThat(editAddressPage.getErrorsLabels()).containsExactly(myAccount.getMyAccountPageGetBillingErrorLabels());
    }

    @Test
    public void shouldEditAndCorrectlyFillInShippingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditShippingAddressPage editShippingAddress = myAccountPage.clickEditShippingAddressEditButtonButton();
        editShippingAddress.fillInShippingAddressForm(myAccount.getEditAddressPageEnterBillingUserName(),
        myAccount.getEditAddressPageEnterBillingUserLastName(),
        myAccount.getEditAddressPageSelectBillingCountryName(),
        myAccount.getEditAddressPageEnterBillingAddress1(),
        myAccount.getEditAddressPageEnterBillingCity(),
        myAccount.getEditAddressPageEnterBillingRegion(),
        myAccount.getEditAddressPageEnterBillingPostCode());
        editShippingAddress.clickShippingSubmitButton();
        assertThat(editShippingAddress.getShippingAddressFormAlert().contains(myAccount.getEditShippingAddressGetShippingAddressFormAlert()));
    }


    @Test
    public void shouldEditAndIncorrectlyFillInShippingAddress() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditShippingAddressPage editShippingAddress = myAccountPage.clickEditShippingAddressEditButtonButton();
        editShippingAddress.fillInShippingAddressForm(myAccount.getEditAddressPageEnterBillingUserName(),
                myAccount.getEditAddressPageEnterBillingUserLastName(),
                myAccount.getEditAddressPageSelectBillingCountryName(),
                myAccount.getEditAddressPageEnterBillingAddress1(),
                myAccount.getEditAddressPageEnterBillingCity(),
                myAccount.getEditAddressPageEnterBillingRegion(),
                myAccount.getMyAccountPageEnterEmptyField());
        editShippingAddress.clickShippingSubmitButton();
        assertTrue(editShippingAddress.isShippingAddressFormAlert());

    }

    @Test
    public void shouldEditAndSendEmptyFillInShippingAddressForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        myAccountPage.clickPaymentAndDeliveryAddressesButton();
        EditShippingAddressPage editShippingAddress = myAccountPage.clickEditShippingAddressEditButtonButton();
        editShippingAddress.clearShippingFormFields();
        editShippingAddress.clickShippingSubmitButton();
        assertThat(editShippingAddress.getErrorLabels()).contains(myAccount.getMyAccountPageGetShippingErrorLabels());
    }

    @Test
    public void shouldCorrectlyFillInChangePasswordAndAccountDetailsForm() {
        LocalDateTime now = LocalDateTime.now();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterRegisterUser(),
                myAccount.getMyAccountPageEnterRegisterUserPassword());
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.fillInChangePasswordForm(
                myAccount.getMyAccountPageGetUserNameText(),
                myAccount.getMyAccountPageGetUserNameText(),
                myAccount.getMyAccountPageGetUserNameText(),
                myAccount.getChangePasswordPageEnterAccountEmail() + now.toString().replaceAll(":", "-"),
                myAccount.getMyAccountPageEnterEmptyField(),
                myAccount.getMyAccountPageEnterEmptyField(),
                myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.submitPassword();
        assertTrue(changePasswordPage.isAccountDetailsChangedAlertAlertDisplayed());

    }

    @Test
    public void shouldIncorrectlyFillInChangePasswordAndAccountDetailsForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.fillInChangePasswordForm(
                myAccount.getMyAccountPageGetUserNameText(),
                myAccount.getMyAccountPageGetUserNameText(),
                myAccount.getMyAccountPageGetUserNameText(),
                myAccount.getChangePasswordPageEnterAccountEmail(),
                myAccount.getMyAccountPageEnterEmptyField(),
                myAccount.getMyAccountPageEnterEmptyField(),
                myAccount.getMyAccountPageEnterEmptyField());
        changePasswordPage.submitPassword();
        assertTrue(changePasswordPage.isMyAccountWelcomeTextDisplayed());

    }

    @Test
    public void shouldSendEmptyFillInChangePasswordAndAccountDetailsForm() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        ChangePasswordPage changePasswordPage = myAccountPage.clickIntoChangePasswordAndAccountDetailsButton();
        changePasswordPage.clearChangePasswordFormFields();
        changePasswordPage.submitPassword();
        assertThat(changePasswordPage.getErrorLabels()).containsExactly(myAccount.getMyAccountPageGetErrorLabels());

    }

}




