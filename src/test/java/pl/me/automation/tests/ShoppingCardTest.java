package pl.me.automation.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.domain.Product;
import pl.me.automation.pages.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShoppingCardTest extends BaseTest{


    @Test
    public void shouldCheckIfFreeShippingIsDisplayedWhenShoppingCardSumMoreThan300() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket1());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(shoppingCardPage.getDeliveryType().contains(shoppingCard.getShoppingCardPageGetDeliveryType()));
    }

    @Test
    public void shouldRemoveProductFromBasket() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket4());
        List<Product> productsFromBasket = shopPage.getProductsInBasket();
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        List<String> productNames = shoppingCardPage.getProductName();
        assertThat(productsFromBasket.stream()
                .map(Product::getName)
                .collect(Collectors.toList()))
                .contains(productNames.toArray(new String[productNames.size()]));
        shoppingCardPage.removeProductsByName(shop.getShopPageAddProductsToBasket3());
        shoppingCardPage = new ShoppingCardPage(webDriver);
        productNames = shoppingCardPage.getProductName();
        assertThat(productsFromBasket.stream()
                .map(Product::getName)
                .collect(Collectors.toList())).contains(productNames.toArray(new String[productNames.size()]));
    }


    @Test
    public void shouldAddToCartFromShopPage() {
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory2());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket5());
        ShopPage shopPage1 = shopPage.selectShopCategory(home.getHomeSelectShopCategory1());
        shopPage1.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        ShopPage shopPage2 = shopPage.selectShopCategory(home.getHomePageSelectShopCategory3());
        shopPage2.addProductsToBasket(shop.getShopPageAddProductsToBasket4());
        ShoppingCardPage shoppingCardPage = shopPage2.clickBasket();
        assertThat(shoppingCardPage.getProductsInBasketNames()).containsExactly(shop.getShopPageAddProductsToBasket5(), shop.getShopPageAddProductsToBasket2(), shop.getShopPageAddProductsToBasket4());
    }

    @Test
    public void shouldAddSameProductSeveralTimesAndGoToShoppingCard() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(shoppingCardPage.getSummaryPrice(Integer.valueOf(shoppingCard.getShoppingCardPageGetSummaryPrice())).equals(shoppingCardPage.getShoppingCardTotalPrice()));
    }

    @Test
    public void shouldAddSameProductWithDifferentFeaturesAndGoToShoppingCard() {
        ShopPage shopPage = homePage.clickShop();
        SearchResultsPage searchResultsPage = shopPage.findProduct(shop.getShopPageFindProduct());
        ShopPage shopPage1 = searchResultsPage.clickSearchResultButton();
        assertThat(shopPage1.getFilteredProductName()).containsExactly(
                shop.getShopPageGetJeansTornBlueJeans(),
                shop.getShopPageGetJeansBlueDenimJeans(),
                shop.getShopPageGetJeansBlackWashedJeans(),
                shop.getShopPageGetJeansBasicBlueJeans(),
                shop.getShopPageGetBlueDenimJeans(),
                shop.getShopPageGetBasicGrayBlueJeans(),
                shop.getShopPageGetTornBlueJeans(),
                shop.getShopPageGetBasicBlueJeans());
    }

    @Test
    public void shouldCheckIfPendingShoppingCardSumsUpWithNewAdded() {
        LocalDateTime now = LocalDateTime.now();
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket1());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        Double totalPrice = shoppingCardPage.getShoppingCardTotalPrice();
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
        shopPage = paymentPage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket1());
        Double productPrice = shopPage.getProductPrice(shop.getShopPageAddProductsToBasket1());
        shoppingCardPage = shopPage.clickBasket();
        assertThat(totalPrice + productPrice).isEqualTo(shoppingCardPage.getShoppingCardTotalPrice());
    }

    @Test
    public void shouldAddCouponCodeAndCheckIfAppliedCorrectly() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        shoppingCardPage.applyCouponCode(shoppingCard.getShoppingCardPageApplyCorrectCouponCode());
        shoppingCardPage = new ShoppingCardPage(webDriver);
        assertTrue(shoppingCardPage.getAppliedCouponSuccessfullyAlertMessage().contains(shoppingCard.getShoppingCardPageGetAppliedCouponSuccessfullyAlertMessage()));
        Double productPriceAmount = shoppingCardPage.getProductPriceAmount();
        Double productCouponAmount = shoppingCardPage.getProductCouponAmount();
        Double productShipping = shoppingCardPage.getProductShippingFirstMethodPrice();
        Double productSumPrice = shoppingCardPage.getProductSumPrice();
        assertThat(productPriceAmount - productCouponAmount + productShipping).isEqualTo(productSumPrice);
    }

    @Test
    public void shouldAddIncorrectCouponCodeAndCheckIfApplied() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        String couponCode = shoppingCardPage.applyCouponCode(shoppingCard.getShoppingCardPageApplyCouponCode());
        shoppingCardPage = new ShoppingCardPage(webDriver);
        assertThat(shoppingCardPage.getAppliedCouponFailAlertMessage().contains(shoppingCard.getShoppingCardPageGetAppliedCouponFailAlertMessageOne()+ couponCode +shoppingCard.getShoppingCardPageGetAppliedCouponFailAlertMessageTwo()));
    }

    @Test
    public void shouldCheckIfShipmentAmountIsCorrectlyCalculated() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket2());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        shoppingCardPage.chooseShippingMethod(Integer.valueOf(shoppingCard.getShoppingCardPageChooseShippingMethod()));
        shoppingCardPage = new ShoppingCardPage(webDriver);
        Double productPriceAmount = shoppingCardPage.getProductPriceAmount();
        Double productSumPrice = shoppingCardPage.getProductSumPrice();
        assertThat(productPriceAmount + shoppingCardPage.getProductShippingFirstMethodPrice()).isEqualTo(productSumPrice);
    }



}







