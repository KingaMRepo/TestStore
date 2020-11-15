package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentPageTest extends Forms {
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
        webDriver.manage().deleteAllCookies();
    }

    @Test
    public void shouldAddCouponCodeAndCheckIfAppliedCorrectly() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.applyCouponCode("AD8PGRAD");
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
        fillInPaymentForm(paymentPage);
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.choosePaymentMethod("payu");
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
        fillInPaymentForm(paymentPage);
        fillInPaymentFormOtherAddress(paymentPage);
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
        fillInPaymentForm(paymentPage);
        fillInPaymentFormOtherAddressIncorrectly(paymentPage);
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getFormValidationErrorText()).isEqualTo("Shipping Kod pocztowy");
    }

    @Test
    public void shouldCorrectlyLogInOnPaymentPageAsRegisteredUser() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.clickLoginButton();
        paymentPage.enterUserNameOrEmail("euookv");
        paymentPage.enterUserPassword("Stefan666^^^");
        paymentPage.clickSubmitButton();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        assertThat(myAccountPage.getMyAccountUserText()).isEqualTo("euookv");
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

    @Test
    public void shouldSelectBankTransferAsPaymentMethod(){
        ShopPage shopPage = homePage.selectShopCategory("Kobieta");
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        String orderNumber = orderPage.getOrderNumber();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.clickLastOrdersButton();
        String orderListLastOrder = myAccountPage.getOrderListLastOrder();
        assertTrue(orderNumber.equals(orderListLastOrder));
    }

    @Test
    public void shouldSelectPayUAsPaymentMethodAndRefreshToCheckIfPaymentMethodChange(){
        ShopPage shopPage = homePage.selectShopCategory("Akcesoria");
        shopPage.addProductsToBasket("Bright Purse With Chain");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod("payu");
        webDriver.navigate().refresh();
        assertThat(paymentPage.checkIfPayURadioButtonIsSelected());

    }

    @Test
    public void shouldSelectStripeAsPaymentMethodAndMakePurchase() {
        ShopPage shopPage = homePage.selectShopCategory("Akcesoria");
        shopPage.addProductsToBasket("Bright Purse With Chain");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod("stripe");
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.findElementInFrame("4242424242424242", "0623", "999");
        OrderPage orderPage = paymentPage.paymentAccept();
        String orderNumber = orderPage.getOrderNumber();
        assertThat(orderNumber).isNotNull();

    }
        @Test
        public void shouldSelectStripeAsPaymentMethodAndMakePurchaseWithoutAcceptingTerms() {
            ShopPage shopPage = homePage.selectShopCategory("Akcesoria");
            shopPage.addProductsToBasket("Bright Purse With Chain");
            ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
            PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
            logInOnPaymentPageAsRegisteredUser(paymentPage);
            paymentPage.choosePaymentMethod("stripe");
            paymentPage.clickNewUPaymentMethodRadioButton();
            paymentPage.findElementInFrame("4242424242424242", "0623", "999");
            paymentPage.paymentAccept();
            new PaymentPage(webDriver);
            assertThat(paymentPage.getUnableToProcessOrderAlertText().contains("Proszę przeczytać i zaakceptować regulamin sklepu aby móc sfinalizować zamówienie."));
        }


    @Test
    public void shouldSelectStripeAsPaymentMethodWithIncorrectCardDate() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod("stripe");
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame("4000000000000127", "0600", "875");
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getStipeCardValidityErrorText().contains("Rok ważności karty upłynął w przeszłości"));
    }

    @Test
    public void shouldSelectStripeAsPaymentMethodWithIncorrectCVC() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod("stripe");
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame("4000000000000119", "0670", "99");
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getStipeCardValidityErrorText().contains("Kod bezpieczeństwa karty jest niekompletny."));
    }

    @Test
    public void shouldSelectStripeAsPaymentMethodWithExpiredCard() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod("stripe");
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame("4000000000000069", "0627", "999");
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getUnableToProcessOrderAlertText().contains("Karta wygasła."));
    }

    @Test
    public void shouldSelectStripeAsPaymentMethodWithIncorrectCardNumber() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod("stripe");
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame("4242424242424241", "0627", "999");
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getStipeCardValidityErrorText().contains("Numer karty nie jest prawidłowym numerem karty kredytowej."));
    }



}








