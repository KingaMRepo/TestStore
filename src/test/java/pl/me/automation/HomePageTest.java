package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.domain.Product;
import pl.me.automation.page.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {
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
    public void shouldDeleteProductsFromWidget() {
        HomePage homePage = new HomePage(webDriver);
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        shopPage.showProductsFromWidget();
        shopPage= shopPage.deleteProductFromWidgetByName("Aqua Wind Breaker");
        shopPage.showProductsFromWidget();
        String totalPrice = shopPage.getWidgetTotalPrice();
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(totalPrice.replace("Kwota: zł", "")).isEqualTo(shoppingCardPage.getShoppingCardTotalPrice()+ "0");
    }
    @Test
        public void shouldAddProductToWishList() {
        homePage.addProductsToWishList(0);
        assertThat(homePage.isWishListPopupMessageDisplayed()).isTrue();
    }

    @Test
    public void shouldRefuseCookie() {
        homePage.refuseCookie();
        assertThat(homePage.isCookieRefuseButtonDisplayed()).isFalse();
    }

    @Test
    public void addRecommendedProductToBasket(){
        homePage.addRecommendedProductsToBasket("DNK Gray Shoes");
        homePage.addRecommendedProductsToBasket("DNK Blue Sport Shoes");
        List<Product> products =  homePage.getProductsInBasket();
        HomePage homePage = new HomePage(webDriver);
        ShoppingCardPage shoppingCardPage = homePage.clickShoppingCard();
        List<String> productNames = shoppingCardPage.getProductName();
        assertThat(productNames).containsExactly(products.stream().map(Product::getName).toArray(String[]::new));
    }

}







