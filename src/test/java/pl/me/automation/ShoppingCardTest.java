package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.domain.Product;
import pl.me.automation.page.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCardTest extends Forms {
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
    public void shouldCheckIfFreeShippingIsDisplayedWhenShoppingCardSumMoreThan300() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(shoppingCardPage.getDeliveryType().contains("Darmowa wysyłka"));
    }

    @Test
    public void shouldRemoveProductFromBasket() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        List<Product> productsFromBasket = shopPage.getProductsInBasket();
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        List<String> productNames = shoppingCardPage.getProductName();
        assertThat(productsFromBasket.stream()
                .map(Product::getName)
                .collect(Collectors.toList()))
                .contains(productNames.toArray(new String[productNames.size()]));
        shoppingCardPage.removeProductsByName("Anchor Bracelet");
        productsFromBasket.removeIf(product -> product.getName().equals("Anchor Bracelet"));
        shoppingCardPage = new ShoppingCardPage(webDriver);
        productNames = shoppingCardPage.getProductName();
        assertThat(productsFromBasket.stream()
                .map(Product::getName)
                .collect(Collectors.toList())).contains(productNames.toArray(new String[productNames.size()]));
    }


    @Test
    public void shouldAddToCartFromShopPage() {
        ShopPage shopPage = homePage.selectShopCategory("Akcesoria");
        shopPage.addProductsToBasket("Boho Bangle Bracelet");
        shopPage.addProductsToBasket("Buddha Bracelet");
        ShopPage shopPage1 = shopPage.selectShopCategory("Kobieta");
        shopPage1.addProductsToBasket("Basic Gray Blue Jeans");
        shopPage1.addProductsToBasket("Basic Blue Jeans");
        ShopPage shopPage2 = shopPage.selectShopCategory("Mężczyzna");
        shopPage2.addProductsToBasket("Aqua Wind Breaker");
        shopPage2.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage2.clickBasket();
        assertThat(shoppingCardPage.getProductsInBasketNames()).containsExactly("Boho Bangle Bracelet", "Buddha Bracelet", "Basic Gray Blue Jeans", "Basic Blue Jeans", "Aqua Wind Breaker", "Basic Blue Jeans");

    }

    @Test
    public void shouldAddSameProductSeveralTimesAndGoToShoppingCard() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(shoppingCardPage.getSummaryPrice(3).equals(shoppingCardPage.getShoppingCardTotalPrice()));
    }

    @Test
    public void shouldAddSameProductWithDifferentFeaturesAndGoToShoppingCard() {
        ShopPage shopPage = homePage.clickShop();
        SearchResultsPage searchResultsPage = shopPage.findProduct("Jeans");
        ShopPage shopPage1 = searchResultsPage.clickSearchResultButton();
        assertThat(shopPage1.getFilteredProductName()).containsExactly("Torn Blue Jeans", "Blue Denim Jeans", "Black Washed Jeans", "Basic Blue Jeans", "Blue Denim Jeans", "Basic Gray Blue Jeans", "Torn Blue Jeans", "Basic Blue Jeans");

    }

    @Test
    public void shouldCheckIfPendingShoppingCardSumsUpWithNewAdded() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Black Jacket");
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        Double totalPrice = shoppingCardPage.getShoppingCardTotalPrice();
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        paymentPage.enterBillingUserName("marek");
        paymentPage.enterBillingUserLastName("nowak");
        paymentPage.selectBillingCountry(1);
        paymentPage.enterBillingUserAddress("Kwiatowa");
        paymentPage.enterBillingUserCity("Poznań");
        paymentPage.enterBillingUserPostCode("60001");
        paymentPage.enterBillingUserPhone("600500400");
        paymentPage.enterBillingUserEmail("qwertt!#12@nazwa.pl");
        paymentPage.enterBillingAccountUsername("kinga");
        paymentPage.enterBillingAccountPassword("&%SIytrdf!90");
        paymentPage.deselectShipToDifferentAddressCheckbox();
        paymentPage.acceptTermsAndConditionsCheckbox();
        shopPage = paymentPage.clickShop();
        shopPage.addProductsToBasket("Black Jacket");
        Double productPrice = shopPage.getProductPrice("Black Jacket");
        shoppingCardPage = shopPage.clickBasket();
        assertThat(totalPrice + productPrice).isEqualTo(shoppingCardPage.getShoppingCardTotalPrice());
    }

    @Test
    public void shouldAddCouponCodeAndCheckIfAppliedCorrectly() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        shoppingCardPage.applyCouponCode("AD8PGRAD");
        shoppingCardPage = new ShoppingCardPage(webDriver);
        assertTrue(shoppingCardPage.getAppliedCouponSuccessfullyAlertMessage().contains("Kupon został pomyślnie użyty."));
        Double productPriceAmount = shoppingCardPage.getProductPriceAmount();
        Double productCouponAmount = shoppingCardPage.getProductCouponAmount();
        Double productShipping = shoppingCardPage.getProductShippingFirstMethod();
        Double productSumPrice = shoppingCardPage.getProductSumPrice();
        assertThat(productPriceAmount - productCouponAmount + productShipping).isEqualTo(productSumPrice);
    }

    @Test
    public void shouldAddIncorrectCouponCodeAndCheckIfApplied() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Anchor Bracelet");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        String couponCode = shoppingCardPage.applyCouponCode("AD8PGRA?");
        shoppingCardPage = new ShoppingCardPage(webDriver);
        assertThat(shoppingCardPage.getAppliedCouponFailAlertMessage().contains("Kupon"+ couponCode +"nie istnieje!"));
    }

    @Test
    public void shouldCheckIfShipmentAmountIsCorrectlyCalculated() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        shoppingCardPage.chooseShippingMethod(0);
        shoppingCardPage = new ShoppingCardPage(webDriver);
        Double productPriceAmount = shoppingCardPage.getProductPriceAmount();
        Double productSumPrice = shoppingCardPage.getProductSumPrice();
        assertThat(productPriceAmount + shoppingCardPage.getProductShippingFirstMethod()).isEqualTo(productSumPrice);

    }



}







