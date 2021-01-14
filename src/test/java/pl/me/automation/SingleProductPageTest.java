package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.HomePage;
import pl.me.automation.page.ShopPage;
import pl.me.automation.page.ShoppingCardPage;
import pl.me.automation.page.SingleProductPage;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SingleProductPageTest extends Forms {

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
    public void shouldSuccessfulAddCommentAndRating() {
        ShopPage shopPage = homePage.clickShop();
        SingleProductPage singleProductPage = shopPage.clickProduct(Integer.valueOf(shop.getShopPageClickProduct3()));
        singleProductPage.clickReviewsButton();
        singleProductPage.addStarRating(singleProduct.getSingleProductPageAddStarRating());
        singleProductPage.enterComment(singleProduct.getSingleProductPageEnterComment());
        singleProductPage.enterUserNameAndEmail(singleProduct.getSingleProductPageEnterUserName(), singleProduct.getSingleProductPageEnterUserEmail());
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getAddedStarRating().contains(singleProduct.getSingleProductPageGetAddedStarRating()));
        assertThat(singleProductPage.getCommentDescription().contains(singleProduct.getSingleProductPageEnterComment()));

    }

    @Test
    public void shouldAddCommentWithoutRating() {
        ShopPage shopPage = homePage.clickShop();
        SingleProductPage singleProductPage = shopPage.clickProduct(Integer.valueOf(shop.getShopPageClickProduct()));
        singleProductPage.clickReviewsButton();
        singleProductPage.enterComment(singleProduct.getSingleProductPageEnterComment());
        singleProductPage.enterUserNameAndEmail(singleProduct.getSingleProductPageEnterUserName(), singleProduct.getSingleProductPageEnterUserEmail());
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getAlertBoxText().contains(singleProduct.getSingleProductPageGetAlertBoxText()));
    }

    @Test
    public void shouldAddRatingWithoutComment() {
        ShopPage shopPage = homePage.clickShop();
        SingleProductPage singleProductPage = shopPage.clickProduct(Integer.valueOf(shop.getShopPageClickProduct3()));
        singleProductPage.clickReviewsButton();
        singleProductPage.addStarRating(singleProduct.getSingleProductPageAddStarRating());
        singleProductPage.enterUserNameAndEmail(singleProduct.getSingleProductPageEnterUserName(), singleProduct.getSingleProductPageEnterUserEmail());
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getNoReviewsText().contains(singleProduct.getSingleProductPageGetNoReviewsText()));
    }


    @Test
    public void shouldSendEmptyCommentForm() {
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory2());
        SingleProductPage singleProductPage = shopPage.clickProduct(Integer.valueOf(shop.getShopPageClickProduct()));
        singleProductPage.clickReviewsButton();
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getAlertBoxText().contains(singleProduct.getSingleProductPageGetAlertBoxText()));
    }

    @Test
    public void shouldAddProductToCartFromSingleProductCardPage(){
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory2());
        SingleProductPage singleProductPage = shopPage.clickProduct(Integer.valueOf(shop.getShopPageClickProduct3()));
        singleProductPage.clickAddToCardSingeProductButton();
        String singleProductName = singleProductPage.getSingleProductName();
        ShoppingCardPage shoppingCardPage = singleProductPage.clickWidget();
        assertThat(singleProductName.equals(shoppingCardPage.getProductsInBasketNames()));
    }

    @Test
    public void shouldAddProductToCartFromRecommendedProductsSection(){
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory1());
        SingleProductPage singleProductPage = shopPage.clickProduct(Integer.valueOf(shop.getShopPageClickProduct()));
        singleProductPage.clickAddToCardSingeProductButton();
        singleProductPage.clickRecommendedProductsButton(Integer.valueOf(singleProduct.getSingleProductPageClickRecommendedProductsButton0()));
        singleProductPage.clickRecommendedProductsButton(Integer.valueOf(singleProduct.getSingleProductPageClickRecommendedProductsButton1()));
        List recommendedProductsName = singleProductPage.getRecommendedProductsName();
        ShoppingCardPage shoppingCardPage = singleProductPage.clickWidget();
        assertThat(recommendedProductsName.equals(shoppingCardPage.getProductName()));

    }


}
