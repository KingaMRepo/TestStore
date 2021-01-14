package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishListTest extends Forms {

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
    public void shouldAddProductToWishlistAndValidateSameIsPresentInWishlistWhenLoginAgain() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsAdminForm(myAccountPage);
        ShopPage shopPage = myAccountPage.clickShop();
        shopPage.addProductsToWishListByIndex(Integer.valueOf(shop.getShopPageAddProductsToWishListByIndex2()));
        WishListPage wishListPage = shopPage.clickWistListPage();
        String wishListProductName = wishListPage.getWishListProductName();
        MyAccountPage myAccountPage1 = wishListPage.clickMyAccount();
        myAccountPage1.logOutFromMyAccountPage();
        fillLoginAsAdminForm(myAccountPage);
        WishListPage wishListPage1 = myAccountPage1.clickWistListPage();
        String wishListProductName1 = wishListPage1.getWishListProductName();
        assertThat(wishListProductName.equals(wishListProductName1));

    }

    @Test
    public void shouldAddProductToWishListFromShopPageAsNotLoggedInUser() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.addProductsToWishListByIndex(Integer.valueOf(shop.getShopPageAddProductsToWishListByIndex3()));
        WishListPage wishListPage = shopPage.clickWistListPage();
        assertThat(wishListPage.getWishListProductName()).contains(shop.getShopPageAddProductsToBasket2());

    }

    @Test
    public void shouldAddProductToWishListFromHomePageAsLoggedInUser() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        fillLoginAsUserForm(myAccountPage);
        myAccountPage.clickHome();
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList()));
        WishListPage wishListPage = homePage.clickWistListPage();
        assertThat(wishListPage.getWishListProductName()).contains(wishList.getWishListPageProduct());

    }

    @Test
    public void shouldAddProductToWishListFromHomePageAsNotLoggedUser() {
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList()));
        WishListPage wishListPage = homePage.clickWistListPage();
        assertThat(wishListPage.getWishListProductName()).contains(wishList.getWishListPageProduct());

    }

    @Test
    public void shouldAddProductFromWishListToCardAndProceedWithThePayment() {
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList()));
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList1()));
        WishListPage wistListPage = homePage.clickWistListPage();
        ShoppingCardPage shoppingCardPage = wistListPage.addProductFromWishListToCard(Integer.valueOf(wishList.getWistListPageAddProductFromWishListToCard()));
        assertThat(shoppingCardPage.getProductName()).containsExactly(wishList.getWishListPageProduct());
        PaymentPage paymentPage = shoppingCardPage.proceedToCheckout();
        logInOnPaymentPageAsRegisteredUser(paymentPage);
        paymentPage.acceptTermsAndConditionsCheckbox();
        OrderPage orderPage = paymentPage.paymentAccept();
        assertThat(orderPage.getWishListProductName().contains(wishList.getWishListPageProduct()));

    }

    @Test
    public void shouldRemoveProductFromWishList() {
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList()));
        homePage.addProductsToWishList(Integer.valueOf(home.getHomePageAddProductsToWishList1()));
        WishListPage wishListPage = homePage.clickWistListPage();
        wishListPage = wishListPage.removeProductsByName(wishList.getWishListPageProduct());
        assertTrue(wishListPage.isRemoveAlertDisplay());

    }
}