package pl.me.automation.tests;

import org.junit.jupiter.api.*;
import pl.me.automation.domain.Product;
import pl.me.automation.pages.*;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HomePageTest extends BaseTest {


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
        assertThat(totalPrice.replace(shoppingCard.getShoppingCardPageReplaceTarget(),shoppingCard.getShoppingCardPageReplacement())).isEqualTo(shoppingCardPage.getShoppingCardTotalPrice() + shoppingCard.getShoppingCardPageZero());
    }

    @Test
        public void shouldAddProductToWishList() {
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList()));
        assertThat(homePage.isWishListPopupMessageDisplayed()).isTrue();
    }


    @Test
    public void shouldAddRecommendedProductToCard(){
        homePage.addRecommendedProductsToBasket(home.getHomePageAddRecommendedProductsToBasket1());
        homePage.addRecommendedProductsToBasket(home.getHomePageAddRecommendedProductsToBasket2());
        List<Product> products =  homePage.getProductsInCard();
        HomePage homePage = new HomePage(webDriver);
        ShoppingCardPage shoppingCardPage = homePage.clickShoppingCard();
        List<String> productNames = shoppingCardPage.getProductName();
        assertThat(productNames).containsExactly(products.stream().map(Product::getName).toArray(String[]::new));
    }

}







