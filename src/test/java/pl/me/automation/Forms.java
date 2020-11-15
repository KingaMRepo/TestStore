package pl.me.automation;

import pl.me.automation.page.EditBillingAddressPage;
import pl.me.automation.page.EditShippingAddressPage;
import pl.me.automation.page.MyAccountPage;
import pl.me.automation.page.PaymentPage;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Forms {

    public void fillLoginAsAdminForm(MyAccountPage myAccountPage) {
        myAccountPage.enterLoginUserNameOrEmail("euookv");
        myAccountPage.enterLoginUserLoginPassword("Stefan666^^^");
        myAccountPage.clickLoginSubmit();
    }

    public void fillLoginAsUserForm(MyAccountPage myAccountPage) {
        myAccountPage.enterLoginUserNameOrEmail("nazwa@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("JO20!x*#Z");
        myAccountPage.clickLoginSubmit();
    }


    public void fillLoginFormWitRememberMeCheckbox(MyAccountPage myAccountPage) {
        myAccountPage.enterLoginUserNameOrEmail("Test02@nazwa.pl");
        myAccountPage.enterLoginUserLoginPassword("Test02!@#$%^");
        myAccountPage.clickLoginRememberMeCheckbox();
        myAccountPage.clickLoginSubmit();
    }

    public void fillInPaymentForm(PaymentPage paymentPage) {
        LocalDateTime now = LocalDateTime.now();
        paymentPage.enterBillingUserName("rafałł" + now.toString().replaceAll(":", "-") + "@nazwa.pl");
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
    }

    public void fillInPaymentFormOtherAddress(PaymentPage paymentPage) {
        paymentPage.enterShippingUserName("rafał");
        paymentPage.enterShippingUserLastName("kwiatkowski");
        paymentPage.selectShippingCountry(3);
        paymentPage.enterShippingAddress("Kwiatowa");
        paymentPage.enterShippingCity("Gdańsk");
        paymentPage.enterShippingRegion("pomorskie");
        paymentPage.enterShippingPostCode("50000");
    }

    public void fillInPaymentFormOtherAddressIncorrectly(PaymentPage paymentPage) {
        paymentPage.enterShippingUserName("rafał");
        paymentPage.enterShippingUserLastName("kwiatkowski");
        paymentPage.selectShippingCountry(3);
        paymentPage.enterShippingAddress("Kwiatowa");
        paymentPage.enterShippingCity("Gdańsk");
        paymentPage.enterShippingRegion("pomorskie");
        paymentPage.enterShippingPostCode("%");
    }

    public void logInOnPaymentPageAsRegisteredUser(PaymentPage paymentPage) {
        paymentPage.clickLoginButton();
        paymentPage.enterUserNameOrEmail("euookv");
        paymentPage.enterUserPassword("Stefan666^^^");
        paymentPage.clickSubmitButton();
    }

    public void changeCorrectlyBillingAddress(EditBillingAddressPage editAddressPage) {
        editAddressPage.enterBillingUserName("Aglaea");
        editAddressPage.enterBillingUserLastName("Preethi");
        editAddressPage.selectBillingCountryName("Albania");
        editAddressPage.enterBillingAddress1("Sun Alley");
        editAddressPage.enterBillingPostCode("30404");
        editAddressPage.enterBillingCity("Wlora");
        editAddressPage.enterBillingRegion("Delvina");
        editAddressPage.enterBillingPhoneNumber("111222333");
    }

    public void changeIncorrectlyBillingAddress(EditBillingAddressPage editAddressPage) {
        editAddressPage.enterBillingUserName("");
        editAddressPage.enterBillingUserLastName("Preethi");
        editAddressPage.selectBillingCountryName("Albania");
        editAddressPage.enterBillingAddress1("Sun Alley");
        editAddressPage.enterBillingPostCode("");
        editAddressPage.enterBillingCity("Wlora");
        editAddressPage.enterBillingRegion("Delvina");
        editAddressPage.enterBillingPhoneNumber("$!");

    }
    public void changeCorrectlyShippingAddress(EditShippingAddressPage editShippingAddress) {
        editShippingAddress.enterShippingUserName("admin");
        editShippingAddress.enterShippingUserLastName("admin");
        editShippingAddress.selectBillingCountryName("Polska");
        editShippingAddress.enterShippingAddress1("Poniedziałkowy Dół");
        editShippingAddress.enterShippingPostalCode("30-231");
        editShippingAddress.enterShippingCity("Kraków");
    }

    public void changeIncorrectlyShippingAddress(EditShippingAddressPage editShippingAddress) {
        editShippingAddress.enterShippingUserName("admin");
        editShippingAddress.enterShippingUserLastName("admin");
        editShippingAddress.selectBillingCountryName("Polska");
        editShippingAddress.enterShippingAddress1("Poniedziałkowy Dół");
        editShippingAddress.enterShippingPostalCode("%&/^");
        editShippingAddress.enterShippingCity("Kraków");
    }
}