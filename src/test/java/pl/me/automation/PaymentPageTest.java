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
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.applyCouponCode(payment.getPaymentPageApplyCouponCode());
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
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillInPaymentFormCorrectly(paymentPage);
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        assertThat(orderPage.isOrderAcceptMessageIsDisplay()).isTrue();
    }

    @Test
    public void shouldTryToRegisterPayWithEmptyForm() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getErrorLabels()).containsExactly(payment.getLabels());
    }

    @Test
    public void shouldIncorrectlyCompletedForm() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillInPaymentFormIncorrectly(paymentPage);
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getBillingNameErrorText()).isEqualTo(payment.getPaymentPageGetBillingNameErrorText());

    }

    @Test
    public void shouldCorrectlyCompleteFormChoosingCountryOtherThanPoland() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillInPaymentFormOtherAddress(paymentPage);
        paymentPage.deselectShipToDifferentAddressCheckbox();
        assertThat(paymentPage.getShippingMethodRate()).isEqualTo(payment.getShippingMethodRate());
    }

    @Test
    public void shouldCorrectlyCompletedFormAndChooseOtherShippingAddress() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillInPaymentFormCorrectly(paymentPage);
        fillInPaymentFormOtherAddress(paymentPage);
        assertThat(paymentPage.isShipToDifferentAddressCheckboxChecked()).isTrue();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        assertThat(orderPage.isReceivedOrderAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldIncorrectlyCompletedFormAndChooseOtherShippingAddress() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        fillInPaymentFormCorrectly(paymentPage);
        fillInPaymentFormOtherAddressIncorrectly(paymentPage);
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getFormValidationErrorText()).isEqualTo(payment.getPaymentPageGetFormValidationErrorText());
    }

    @Test
    public void shouldCorrectlyLogInOnPaymentPageAsRegisteredUser() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.clickLoginButton();
        paymentPage.enterUserNameOrEmail(payment.getPaymentPageEnterUserNameOrEmail());
        paymentPage.enterUserPassword(payment.getPaymentPageEnterUserPassword());
        paymentPage.clickSubmitButton();
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        assertThat(myAccountPage.getMyAccountUserText()).isEqualTo(payment.getPaymentPageMyAccountUserText());
    }

    @Test
    public void shouldIncorrectlyLogInOnPaymentPageAsRegisteredUser() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.clickLoginButton();
        paymentPage.enterUserNameOrEmail(payment.getPaymentPageEnterUserNameOrEmail());
        paymentPage.enterUserPassword(payment.getPaymentPageEnterUserIncorrectPassword());
        paymentPage.clickSubmitButton();
        assertThat(paymentPage.getErrorText()).contains(payment.getPaymentPageGetErrorText());
    }

    @Test
    public void shouldCorrectlyUsePasswordReminder() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.clickLoginButton();
        MyAccountPage myAccountPage = paymentPage.clickLostPasswordButton();
        myAccountPage.enterLoginOrEmailToLostPasswordUserField(payment.getMyAccountPageEnterLoginOrEmailToLostPasswordUserField());
        myAccountPage.clickResetPasswordSubmitButton();
        assertThat(myAccountPage.getResetPasswordSuccessSendAlert()).isEqualTo(payment.getMyAccountPageGetResetPasswordSuccessSendAlert());
    }

    @Test
    public void shouldSelectBankTransferAsPaymentMethod(){
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory1());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
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
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory2());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethod());
        webDriver.navigate().refresh();
        assertThat(paymentPage.checkIfPayURadioButtonIsSelected());

    }

    @Test
    public void shouldSelectStripeAsPaymentMethodAndMakePurchase() {
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory2());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.findElementInFrame(payment.getPaymentPageCorrectCardDetailsCardNumber(), payment.getPaymentPageCorrectCardDetailsCardDate(), payment.getPaymentPageCorrectCardDetailsCardCVC());
        OrderPage orderPage = paymentPage.paymentAccept();
        String orderNumber = orderPage.getOrderNumber();
        assertThat(orderNumber).isNotNull();

    }
        @Test
        public void shouldSelectStripeAsPaymentMethodAndMakePurchaseWithoutAcceptingTerms() {
            ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory2());
            shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
            ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
            PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
            logInOnPaymentPageAsRegisteredUser(paymentPage);
            paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
            paymentPage.clickNewUPaymentMethodRadioButton();
            paymentPage.findElementInFrame(payment.getPaymentPageCorrectCardDetailsCardNumber(), payment.getPaymentPageCorrectCardDetailsCardDate(), payment.getPaymentPageCorrectCardDetailsCardCVC());
            paymentPage.paymentAccept();
            new PaymentPage(webDriver);
            assertThat(paymentPage.getUnableToProcessOrderAlertText().contains(payment.getPaymentPageGetUnableToProcessOrderAlertText()));
        }


    @Test
    public void shouldSelectStripeAsPaymentMethodWithIncorrectCVC() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame(payment.getPaymentPageIncompleteCardDetailsCardNumber(), payment.getPaymentPageIncompleteCardDetailsCardDate(), payment.getPaymentPageIncompleteCardDetailsCardCVC());
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getStripeCardValidityErrorText().contains(payment.getPaymentPageGetStripeCardValidityErrorTextCVCIncomplete()));
    }

    @Test
    public void shouldSelectStripeAsPaymentMethodWithExpiredCard() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame(payment.getPaymentPageExpiredCard2DetailsCardNumber(), payment.getPaymentPageExpiredCardDetailsCardDate(), payment.getPaymentPageIncorrectCardDetailsCardCVC2());
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getUnableToProcessOrderAlertText().contains(payment.getPaymentPageGetUnableToProcessOrderAlertTextExpiredCard()));
    }

    @Test
    public void shouldSelectStripeAsPaymentMethodWithIncorrectCardNumber() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame(payment.getPaymentPageIncorrectCardDetailsCardNumber2(), payment.getPaymentPageIncorrectCardDetailsCardDate2(), payment.getPaymentPageIncorrectCardDetailsCardCVC2());
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getStripeCardValidityErrorText().contains(payment.getPaymentPageGetInvalidStripeCardValidityErrorText()));
    }



}








