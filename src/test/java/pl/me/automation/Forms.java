package pl.me.automation;

import pl.me.automation.page.EditBillingAddressPage;
import pl.me.automation.page.EditShippingAddressPage;
import pl.me.automation.page.MyAccountPage;
import pl.me.automation.page.PaymentPage;
import pl.me.automation.utils.TestDataReader;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Forms extends TestDataReader {

    public void fillLoginAsAdminForm(MyAccountPage myAccountPage) {
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        myAccountPage.clickLoginSubmit();
    }

    public void fillLoginAsUserForm(MyAccountPage myAccountPage) {
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageFillLoginAsUserFormEnterEmail());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageFillLoginAsUserFormEnterPassword());
        myAccountPage.clickLoginSubmit();
    }


    public void fillLoginFormWitRememberMeCheckbox(MyAccountPage myAccountPage) {
        myAccountPage.enterLoginUserNameOrEmail(myAccount.getMyAccountPageRememberMeCheckboxEnterEmail());
        myAccountPage.enterLoginUserLoginPassword(myAccount.getMyAccountPageRememberMeCheckboxEnterPassword());
        myAccountPage.clickLoginRememberMeCheckbox();
        myAccountPage.clickLoginSubmit();
    }

    public void fillInPaymentFormCorrectly(PaymentPage paymentPage) {
        LocalDateTime now = LocalDateTime.now();
        paymentPage.enterBillingUserName(payment.getPaymentPageEnterBillingUserName() + now.toString().replaceAll(":", "-"));
        paymentPage.enterBillingUserLastName(payment.getPaymentPageEnterBillingUserLastName());
        paymentPage.selectBillingCountry(payment.getPaymentPageSelectBillingCountry());
        paymentPage.enterBillingUserAddress(payment.getPaymentPageEnterBillingUserAddress());
        paymentPage.enterBillingUserCity(payment.getBillingCity());
        paymentPage.enterBillingProvince(payment.getPaymentPageEnterProvince());
        paymentPage.enterBillingUserPostCode(payment.getPaymentPageEnterBillingUserPostCode());
        paymentPage.enterBillingUserPhone(payment.getPaymentPageEnterBillingUserPhone());
        paymentPage.enterBillingUserEmail(payment.getPaymentPageEnterBillingAccountUsername() + now.toString().replaceAll(":", "-") + payment.getPaymentPageEnterBillingUserEmailDomain());
        paymentPage.enterBillingAccountUsername(payment.getPaymentPageEnterBillingAccountUsername() + UUID.randomUUID().toString());
        paymentPage.enterBillingAccountPassword(payment.getPaymentPageEnterUserPassword() + now.toString().replaceAll(":", "-"));

    }

    public void fillInPaymentFormIncorrectly(PaymentPage paymentPage) {
        LocalDateTime now = LocalDateTime.now();
        paymentPage.enterBillingUserName(myAccount.getMyAccountPageEnterEmptyField());
        paymentPage.enterBillingUserLastName(payment.getPaymentPageEnterBillingUserLastName());
        paymentPage.selectBillingCountry(payment.getPaymentPageSelectBillingCountry());
        paymentPage.enterBillingUserAddress(payment.getPaymentPageEnterBillingUserAddress());
        paymentPage.enterBillingUserCity(payment.getBillingCity());
        paymentPage.enterBillingUserPostCode(payment.getPaymentPageEnterBillingUserPostCode());
        paymentPage.enterBillingUserPhone(payment.getPaymentPageEnterBillingUserPhone());
        paymentPage.enterBillingUserEmail(payment.getPaymentPageEnterBillingAccountUsername() + now.toString().replaceAll(":", "-") + payment.getPaymentPageEnterBillingUserEmailDomain());
        paymentPage.enterBillingAccountUsername(payment.getPaymentPageEnterBillingAccountUsername() + UUID.randomUUID().toString());
        paymentPage.enterBillingAccountPassword(payment.getPaymentPageEnterUserPassword() + now.toString().replaceAll(":", "-"));

    }

    public void fillInPaymentFormOtherAddress(PaymentPage paymentPage) {
        paymentPage.enterShippingUserName(payment.getPaymentPageEnterUserNameOrEmail());
        paymentPage.enterShippingUserLastName(payment.getPaymentPageEnterBillingUserLastName());
        paymentPage.selectShippingCountry(payment.getPaymentPageSelectBillingCountry());
        paymentPage.enterShippingAddress(payment.getPaymentPageEnterBillingUserAddress());
        paymentPage.enterShippingCity(payment.getBillingCity());
        paymentPage.enterShippingRegion(payment.getPaymentPageEnterProvince());
        paymentPage.enterShippingPostCode(payment.getPaymentPageEnterBillingUserPostCode());
    }

    public void fillInPaymentFormOtherAddressIncorrectly(PaymentPage paymentPage) {
        paymentPage.enterShippingUserName(payment.getPaymentPageEnterUserNameOrEmail());
        paymentPage.enterShippingUserLastName(payment.getPaymentPageEnterBillingUserLastName());
        paymentPage.selectShippingCountry(payment.getPaymentPageSelectBillingCountry());
        paymentPage.enterShippingAddress(payment.getPaymentPageEnterBillingUserAddress());
        paymentPage.enterShippingCity(payment.getBillingCity());
        paymentPage.enterShippingRegion(payment.getPaymentPageEnterProvince());
        paymentPage.enterShippingPostCode(payment.getPaymentPageEnterIncorrectUserPostCode());
    }

    public void logInOnPaymentPageAsRegisteredUser(PaymentPage paymentPage) {
        paymentPage.clickLoginButton();
        paymentPage.enterUserNameOrEmail(payment.getPaymentPageEnterUserNameOrEmail());
        paymentPage.enterUserPassword(payment.getPaymentPageEnterUserPassword());
        paymentPage.clickSubmitButton();
    }

    public void changeCorrectlyBillingAddress(EditBillingAddressPage editAddressPage) {
        editAddressPage.enterBillingUserName(myAccount.getEditAddressPageEnterBillingUserLastName());
        editAddressPage.enterBillingUserLastName(myAccount.getEditAddressPageEnterBillingUserName());
        editAddressPage.selectBillingCountryName(myAccount.getEditAddressPageSelectBillingCountryName());
        editAddressPage.enterBillingAddress1(myAccount.getEditAddressPageEnterBillingAddress1());
        editAddressPage.enterBillingPostCode(myAccount.getEditAddressPageEnterBillingPostCode());
        editAddressPage.enterBillingCity(myAccount.getEditAddressPageEnterBillingCity());
        editAddressPage.enterBillingRegion(myAccount.getEditAddressPageEnterBillingRegion());
        editAddressPage.enterBillingPhoneNumber(myAccount.getEditAddressPageEnterBillingPhoneNumber());
    }

    public void changeIncorrectlyBillingAddress(EditBillingAddressPage editAddressPage) {
        editAddressPage.enterBillingUserName(myAccount.getEditAddressPageEnterBillingUserName());
        editAddressPage.enterBillingUserLastName(myAccount.getEditAddressPageEnterBillingUserLastName());
        editAddressPage.selectBillingCountryName(myAccount.getEditAddressPageSelectBillingCountryName());
        editAddressPage.enterBillingAddress1(myAccount.getEditAddressPageEnterBillingAddress1());
        editAddressPage.enterBillingPostCode(myAccount.getEditAddressPageEnterBillingPostCode());
        editAddressPage.enterBillingCity(myAccount.getEditAddressPageEnterBillingCity());
        editAddressPage.enterBillingRegion(myAccount.getEditAddressPageEnterBillingRegion());
        editAddressPage.enterBillingPhoneNumber(myAccount.getEditAddressPageEnterIncorrectBillingPhoneNumber());

    }
    public void changeCorrectlyShippingAddress(EditShippingAddressPage editShippingAddress) {
        editShippingAddress.enterShippingUserName(myAccount.getEditAddressPageEnterBillingUserName());
        editShippingAddress.enterShippingUserLastName(myAccount.getEditAddressPageEnterBillingUserLastName());
        editShippingAddress.selectBillingCountryName(myAccount.getEditAddressPageSelectBillingCountryName());
        editShippingAddress.enterShippingAddress1(myAccount.getEditAddressPageEnterBillingAddress1());
        editShippingAddress.enterShippingState(myAccount.getEditAddressPageEnterBillingRegion());
        editShippingAddress.enterShippingPostalCode(myAccount.getEditAddressPageEnterBillingPostCode());

    }

    public void changeIncorrectlyShippingAddress(EditShippingAddressPage editShippingAddress) {
        editShippingAddress.enterShippingUserName(myAccount.getEditAddressPageEnterBillingUserLastName());
        editShippingAddress.enterShippingUserLastName(myAccount.getEditAddressPageEnterBillingUserLastName());
        editShippingAddress.selectBillingCountryName(myAccount.getEditAddressPageSelectBillingCountryName());
        editShippingAddress.enterShippingAddress1(myAccount.getEditAddressPageEnterBillingAddress1());
        editShippingAddress.enterShippingPostalCode(myAccount.getEditShippingAddressEnterShippingPostalCode());
        editShippingAddress.enterShippingCity(myAccount.getEditAddressPageEnterBillingCity());
    }
}