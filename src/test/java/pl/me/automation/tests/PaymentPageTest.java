package pl.me.automation.tests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.me.automation.pages.*;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentPageTest extends BaseTest {

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
        LocalDateTime now = LocalDateTime.now();
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
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
        assertThat(orderPage.isOrderAcceptMessageIsDisplay()).isTrue();
    }

    @Test
    public void shouldRegisterPaymentWithEmptyForm() {
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
    public void shouldIncorrectlyCompleteForm() {
        LocalDateTime now = LocalDateTime.now();
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
        paymentPage.fillInPaymentForm(
        myAccount.getMyAccountPageEnterEmptyField(),
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
        paymentPage.paymentAccept();
        assertThat(paymentPage.getBillingNameErrorText()).isEqualTo(payment.getPaymentPageGetBillingNameErrorText());

    }

    @Test
    public void shouldCorrectlyCompletedFormAndChooseOtherShippingAddress() {
        LocalDateTime now = LocalDateTime.now();
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
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
        paymentPage.fillInShippingForm(payment.getPaymentPageEnterUserNameOrEmail(),
        payment.getPaymentPageEnterBillingUserLastName(),
        payment.getPaymentPageSelectBillingCountry(),
        payment.getPaymentPageEnterBillingUserAddress(),
        payment.getBillingCity(),
        payment.getPaymentPageEnterProvince(),
        payment.getPaymentPageEnterBillingUserPostCode());
        assertThat(paymentPage.isShipToDifferentAddressCheckboxChecked()).isTrue();
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        assertThat(orderPage.isReceivedOrderAlertDisplayed()).isTrue();
    }

    @Test
    public void shouldIncorrectlyCompletedFormAndChooseOtherShippingAddress() {
        LocalDateTime now = LocalDateTime.now();
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage1 = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage1.proceedToCheckout();
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
        paymentPage.fillInShippingForm(
        payment.getPaymentPageEnterUserNameOrEmail(),
        payment.getPaymentPageEnterBillingUserLastName(),
        payment.getPaymentPageSelectBillingCountry(),
        payment.getPaymentPageEnterBillingUserAddress(),
        payment.getBillingCity(),
        payment.getPaymentPageEnterProvince(),
        payment.getPaymentPageEnterIncorrectUserPostCode());
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
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        assertThat(myAccountPage.getMyAccountUserText()).isEqualTo(payment.getPaymentPageMyAccountUserText());
    }

    @Test
    public void shouldIncorrectlyLogInOnPaymentPageAsRegisteredUser() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserIncorrectPassword());
        assertThat(paymentPage.getErrorText()).contains(payment.getPaymentPageGetErrorText());
    }

    @Test
    public void shouldCorrectlyUsePasswordReminder() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        MyAccountPage myAccountPage = paymentPage.fillInLostPasswordRecoveryForm(payment.getMyAccountPageEnterLoginOrEmailToLostPasswordUserField());
        assertThat(myAccountPage.getResetPasswordSuccessSendAlert()).isEqualTo(payment.getMyAccountPageGetResetPasswordSuccessSendAlert());
    }

    @Test
    public void shouldSelectBankTransferAsPaymentMethod(){
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory1());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
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
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
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
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
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
            paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
            paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
            paymentPage.clickNewUPaymentMethodRadioButton();
            paymentPage.findElementInFrame(payment.getPaymentPageCorrectCardDetailsCardNumber(), payment.getPaymentPageCorrectCardDetailsCardDate(), payment.getPaymentPageCorrectCardDetailsCardCVC());
            paymentPage.paymentAccept();
            new PaymentPage(webDriver);
            assertThat(paymentPage.getUnableToProcessOrderAlertText().contains(payment.getPaymentPageGetUnableToProcessOrderAlertText()));
        }

    @Test
    public void shouldSelectStripeAsPaymentMethodWithIncorrectCardDate() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
        paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame(payment.getPaymentPageExpiredCardDetailsCardNumber(), payment.getPaymentPageExpiredCardDetailsCardDate(), payment.getPaymentPageCorrectCardDetailsCardCVC());
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getStripeCardValidityErrorText().contains(payment.getPaymentPageGetStripeCardValidityErrorText()));
    }


    @Test
    public void shouldSelectStripeAsPaymentMethodWithIncorrectCVC() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
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
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
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
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
        paymentPage.choosePaymentMethod(payment.getPaymentPageChoosePaymentMethodStripe());
        paymentPage.clickNewUPaymentMethodRadioButton();
        paymentPage.findElementInFrame(payment.getPaymentPageIncorrectCardDetailsCardNumber2(), payment.getPaymentPageIncorrectCardDetailsCardDate2(), payment.getPaymentPageIncorrectCardDetailsCardCVC2());
        paymentPage.acceptTermsAndConditionsCheckbox();
        paymentPage.paymentAccept();
        assertThat(paymentPage.getStripeCardValidityErrorText().contains(payment.getPaymentPageGetInvalidStripeCardValidityErrorText()));
    }



}








