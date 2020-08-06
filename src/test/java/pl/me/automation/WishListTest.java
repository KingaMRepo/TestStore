package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.HomePage;
import pl.me.automation.page.ShoppingCardPage;
import pl.me.automation.page.WishListPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishListTest {
    private WebDriver webDriver;
    private HomePage homePage;

    @BeforeEach
    public void init(){
        webDriver = WebDriverType.CHROME.create();
        webDriver.get("https://www.storetestproject.hekko24.pl/");
        homePage = new HomePage(webDriver);
        homePage.clickCookie();
    }
    @AfterEach
    public void destroy(){
        //webDriver.close();
    }
  @Test
  public void shouldAddProductFromWishListToCard() {
      homePage.addProductsToWishList(0);
      homePage.addProductsToWishList(1);
      WishListPage wistListPage = homePage.clickWistListPage();
      ShoppingCardPage shoppingCardPage = wistListPage.addProductFromWishListToCard(0);
      assertThat(shoppingCardPage.getProductName()).containsExactly("DNK Gray Shoes");
  }

  @Test
  public void shouldRemoveProductFromWishList(){
      homePage.addProductsToWishList(0);
      homePage.addProductsToWishList(1);
      WishListPage wishListPage = homePage.clickWistListPage();
      wishListPage = wishListPage.removeProductsByName("DNK Gray Shoes");
      assertTrue(wishListPage.isRemoveAlertDisplay());
  }
}