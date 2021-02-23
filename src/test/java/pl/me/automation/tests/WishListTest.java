package pl.me.automation.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.pages.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WishListTest extends BaseTest{

    @Test
    public void shouldAddProductToWishlistAndValidateSameIsPresentInWishlistWhenLoginAgain() {
        MyAccountPage myAccountPage = homePage.clickMyAccount();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
        ShopPage shopPage = myAccountPage.clickShop();
        shopPage.addProductsToWishListByIndex(Integer.valueOf(shop.getShopPageAddProductsToWishListByIndex2()));
        WishListPage wishListPage = shopPage.clickWistListPage();
        String wishListProductName = wishListPage.getWishListProductName();
        MyAccountPage myAccountPage1 = wishListPage.clickMyAccount();
        myAccountPage1.logOutFromMyAccountPage();
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterLoginUserNameOrEmail1(),
                myAccount.getMyAccountPageEnterLoginUserLoginPassword1());
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
        myAccountPage.fillInLoginAsUserForm(myAccount.getMyAccountPageEnterRegisterUser(),
                myAccount.getMyAccountPageEnterRegisterUserPassword());
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
        paymentPage.logInOnPaymentPageAsRegisteredUser(payment.getPaymentPageEnterUserNameOrEmail(), payment.getPaymentPageEnterUserPassword());
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