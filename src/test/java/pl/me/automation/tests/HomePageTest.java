package pl.me.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.domain.Product;
import pl.me.automation.pages.*;
import pl.me.automation.utils.TestDataReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HomePageTest extends BaseTest {


    @Test
    public void shouldDeleteProductsFromWidget() {
        reports.createTest("HomePageTest - DeleteProductsFromWidget");
        HomePage homePage = new HomePage(webDriver);
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket3());
        shopPage.addProductsToBasket(shop.getShopPageAddProductsToBasket4());
        shopPage.showProductsFromWidget();
        shopPage= shopPage.deleteProductFromWidgetByName(shop.getShopPageAddProductsToBasket4());
        shopPage.showProductsFromWidget();
        String totalPrice = shopPage.getWidgetTotalPrice();
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(totalPrice.replace(shoppingCard.getShoppingCardPageReplaceTarget(),shoppingCard.getShoppingCardPageReplacement())).isEqualTo(shoppingCardPage.getShoppingCardTotalPrice() + shoppingCard.getShoppingCardPageZero());
    }

    @Test
        public void shouldAddProductToWishList() {
        reports.createTest("HomePageTest - AddProductToWishList");
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList()));
        assertThat(homePage.isWishListPopupMessageDisplayed()).isTrue();
    }


    @Test
    public void shouldAddRecommendedProductToBasket(){
        reports.createTest("HomePageTest - AddRecommendedProductToBasket");
        homePage.addRecommendedProductsToBasket(home.getHomePageAddRecommendedProductsToBasket1());
        homePage.addRecommendedProductsToBasket(home.getHomePageAddRecommendedProductsToBasket2());
        List<Product> products =  homePage.getProductsInBasket();
        HomePage homePage = new HomePage(webDriver);
        ShoppingCardPage shoppingCardPage = homePage.clickShoppingCard();
        List<String> productNames = shoppingCardPage.getProductName();
        assertThat(productNames).containsExactly(products.stream().map(Product::getName).toArray(String[]::new));
    }

}







