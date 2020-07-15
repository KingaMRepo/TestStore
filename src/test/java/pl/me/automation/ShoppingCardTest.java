package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.domain.Product;
import pl.me.automation.page.HomePage;
import pl.me.automation.page.ShopPage;
import pl.me.automation.page.ShoppingCardPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCardTest {
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
    public void shouldAddProductsFromRecommendedProductSection() {
        homePage.addProductToCardAndGoToShoppingCard();

    }

    @Test
    public void shouldCheckIfFreeShippingIsDisplayedWhenShoppingCardSumMoreThan300(){
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        shopPage.addProductsToBasket("Basic Blue Jeans");
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        assertThat(shoppingCardPage.getDeliveryType().contains("Darmowa wysyłka"));
    }

    @Test
    public void shouldRemoveProductFromBasket(){
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToBasket("Anchor Bracelet");
        shopPage.addProductsToBasket("Aqua Wind Breaker");
        List<Product> productsFromBasket = shopPage.getProductsInBasket();
        ShoppingCardPage shoppingCardPage = shopPage.clickBasket();
        List<String> productNames = shoppingCardPage.getProductName();
        shoppingCardPage.removeProductsByName("Anchor Bracelet");
        //assertThat()
        //productsFromBasket.stream().map(Product::g).collect(Collectors.toList())
    }




}


