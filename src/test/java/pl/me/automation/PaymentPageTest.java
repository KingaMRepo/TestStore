package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentPageTest extends PaymentForm {
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

    //Payment tests
    @Test
    public void shouldAddCouponCodeAndCheckIfAppliedCorrectly() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.addCouponCode("k3zmxkuk");
        paymentPage = new PaymentPage(webDriver);
        assertTrue(paymentPage.isAppliedCouponAlertMessageDisplayed());
        Double priceAmount = paymentPage.getProductPriceAmount();
        Double couponAmount = paymentPage.getProductCouponAmount();
        Double productShipping = paymentPage.getProductShipping();
        Double productSumPrice = paymentPage.getProductSumPrice();
        assertThat(priceAmount - couponAmount + productShipping).isEqualTo(productSumPrice);
    }

    @Test
    public void shouldSuccessfulRegister() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillPaymentForm(paymentPage);
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        assertThat(orderPage.isOrderAcceptMessageIsDisplay()).isTrue();
    }

    @Test
    public void shouldTryToRegisterPayWithEmptyForm() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getErrorLabels()).containsExactly("Billing Imię","Billing Nazwisko", "Billing Ulica", "Billing Miasto",
                "Billing Telefon", "Billing Adres email", "Konto użytkownika", "Utwórz hasło do konta", "Billing Kod pocztowy" );
    }

    @Test
    public void shouldIncorrectlyCompletedForm() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.enterBillingUserName("jan");
        paymentPage.enterBillingUserLastName("nowak");
        paymentPage.selectBillingCountry(1);
        paymentPage.enterBillingUserAddress("Kwiatowa");
        paymentPage.enterBillingUserCity("Poznań");
        paymentPage.enterBillingUserPostCode("60001");
        paymentPage.enterBillingUserPhone("600500400");
        paymentPage.enterBillingUserEmail("qwert!#11nazwa.pl");
        paymentPage.enterBillingAccountUsername("kinga");
        paymentPage.enterBillingAccountPassword("&%SIytrdf!90");
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        assertThat(orderPage.isOrderAcceptMessageIsDisplay()).isTrue();
    }

    @Test
    public void shouldCorrectlyCompleteFormChoosingCountryOtherThanPoland() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.enterBillingUserName("jan");
        paymentPage.enterBillingUserLastName("nowak");
        paymentPage.selectBillingCountry(3);
        paymentPage.enterBillingUserAddress("Kwiatowa");
        paymentPage.enterBillingUserCity("Poznań");
        paymentPage.enterBillingUserPostCode("60001");
        paymentPage.enterBillingUserPhone("600500400");
        paymentPage.enterBillingUserEmail("qwert!#11@nazwa.pl");
        paymentPage.enterBillingAccountUsername("kinga");
        paymentPage.enterBillingAccountPassword("&%SIytrdf!90");
        paymentPage.deselectShipToDifferentAddressCheckbox();
        assertThat(paymentPage.getShippingMethodRate()).isEqualTo("Płaska Stawka");
    }

    @Test
    public void shouldCorrectlyCompletedFormAndChooseOtherShippingAddress() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillPaymentForm(paymentPage);
        fillPaymentFormOtherAddress(paymentPage);
        assertThat(paymentPage.isShipToDifferentAddressCheckboxChecked()).isTrue();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        assertThat(orderPage.isReceivedOrderAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldIncorrectlyCompletedFormAndChooseOtherShippingAddress() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillPaymentForm(paymentPage);
        fillPaymentFormOtherAddressIncorrectly(paymentPage);
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getFormValidationErrorText()).isEqualTo("Shipping Kod pocztowy");
    }

//Payment tests - registered user login
    @Test
    public void shouldCorrectlyLogInOnPaymentPageAsRegisteredUser() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.clickLoginButton();
        paymentPage.enterUserNameOrEmail("user6781");
        paymentPage.enterUserPassword("gY4+/hDRHb%u7");
        paymentPage.clickSubmitButton();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        assertThat(myAccountPage.getMyAccountUserText()).isEqualTo("user6781");
    }

    @Test
    public void shouldIncorrectlyLogInOnPaymentPageAsRegisteredUser() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.clickLoginButton();
        paymentPage.enterUserNameOrEmail("user123");
        paymentPage.enterUserPassword("gY4+777ToI");
        paymentPage.clickSubmitButton();
        assertThat(paymentPage.getErrorText()).contains("ERROR");
    }

    @Test
    public void shouldCorrectlyUsePasswordReminder() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.clickLoginButton();
        MyAccountPage myAccountPage = paymentPage.clickLostPasswordButton();
        myAccountPage.enterLoginOrEmailToLostPasswordUserField("user78@nazwa.pl");
        myAccountPage.clickResetPasswordSubmitButton();
        assertThat(myAccountPage.getResetPasswordSuccessSendAlert()).isEqualTo("E-mail z linkiem do zresetowania hasła został wysłany na adres przypisany do twojego konta, może minąć kilka minut zanim pojawi się on w twojej skrzynce. Proszę poczekaj co najmniej 10 minut przed ponowną próbą resetu hasła.");
    }


    //Payment methods tests
    @Test
    public void shouldSelectionBankTransferAsPaymentMethodAcceptPurchaseAndRefreshPage() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillPaymentForm(paymentPage);
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        //orderPage.refreshPage();
    }


}








