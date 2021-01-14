package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.domain.Product;
import pl.me.automation.page.*;
import pl.me.automation.utils.TestDataReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends TestDataReader {
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
        //webDriver.quit();
    }


    @Test
    public void shouldDeleteProductsFromWidget() {
        HomePage homePage = new HomePage(webDriver);
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket4());
        shopPage.showProductsFromWidget();
        shopPage= shopPage.deleteProductFromWidgetByName(shop.getShopPageAddProductsToBasket4());
        shopPage.showProductsFromWidget();
        String totalPrice = shopPage.getWidgetTotalPrice();
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(totalPrice.replace(shoppingCard.getShoppingCardPageReplaceTarget(), shoppingCard.getShoppingCardPageReplacement())).isEqualTo(shoppingCardPage.getShoppingCardTotalPrice() + shoppingCard.getShoppingCardPageZero());
    }

    @Test
        public void shouldAddProductToWishList() {
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList()));
        assertThat(homePage.isWishListPopupMessageDisplayed()).isTrue();
    }

    @Test
    public void shouldRefuseCookie() {
        homePage.refuseCookie();
        assertThat(homePage.isCookieRefuseButtonDisplayed()).isFalse();
    }

    @Test
    public void addRecommendedProductToBasket(){
        homePage.addRecommendedProductsToBasket(home.getHomePageAddRecommendedProductsToBasket1());
        homePage.addRecommendedProductsToBasket(home.getHomePageAddRecommendedProductsToBasket2());
        List<Product> products =  homePage.getProductsInBasket();
        HomePage homePage = new HomePage(webDriver);
        ShoppingCardPage shoppingCardPage = homePage.clickShoppingCard();
        List<String> productNames = shoppingCardPage.getProductName();
        assertThat(productNames).containsExactly(products.stream().map(Product::getName).toArray(String[]::new));
    }

}







