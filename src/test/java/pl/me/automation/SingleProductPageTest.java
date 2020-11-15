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

public class SingleProductPageTest {

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
        SingleProductPage singleProductPage = shopPage.clickProduct(0);
        singleProductPage.clickReviewsButton();
        singleProductPage.addStarRating("star-2");
        singleProductPage.enterComment("Readymade hexagon fingerstache cornhole waistcoat.");
        singleProductPage.enterUserNameAndEmail("ann", "ann@gmail.com");
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getAddedStarRating().contains("Oceniony 2 na 5."));
        assertThat(singleProductPage.getCommentDescription().contains("Readymade hexagon fingerstache cornhole waistcoat."));

    }

    @Test
    public void shouldAddCommentWithoutRating() {
        ShopPage shopPage = homePage.clickShop();
        SingleProductPage singleProductPage = shopPage.clickProduct(0);
        singleProductPage.clickReviewsButton();
        singleProductPage.enterComment("Readymade hexagon fingerstache cornhole waistcoat.");
        singleProductPage.enterUserNameAndEmail("ann", "ann@gmail.com");
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getAlertBoxText().contains("Proszę wybrać ocenę"));
    }

    @Test
    public void shouldAddRatingWithoutComment() {
        ShopPage shopPage = homePage.clickShop();
        SingleProductPage singleProductPage = shopPage.clickProduct(0);
        singleProductPage.clickReviewsButton();
        singleProductPage.addStarRating("star-1");
        singleProductPage.enterUserNameAndEmail("ann", "ann@gmail.com");
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getNoReviewsText().contains("Na razie nie ma opinii o produkcie."));
    }


    @Test
    public void shouldSendEmptyCommentForm() {
        ShopPage shopPage = homePage.selectShopCategory("Akcesoria");
        SingleProductPage singleProductPage = shopPage.clickProduct(2);
        singleProductPage.clickReviewsButton();
        singleProductPage.clickCommentSubmitButton();
        assertThat(singleProductPage.getAlertBoxText().contains("Proszę wybrać ocenę"));
    }

    @Test
    public void shouldAddManyComments() {
        LocalDateTime now = LocalDateTime.now();
        ShopPage shopPage = homePage.selectShopCategory("Akcesoria");
        SingleProductPage singleProductPage = shopPage.clickProduct(1);
        singleProductPage.clickReviewsButton();
        singleProductPage.addStarRating("star-2");
        singleProductPage.enterComment("Readymade fingerstache waistcoat."+now.toString().replaceAll(":", "-"));
        singleProductPage.enterUserNameAndEmail("ann", "ann@gmail.com");
        singleProductPage.clickCommentSubmitButton();
        singleProductPage.enterComment("imsum waistcoat shirt"+now.toString().replaceAll(":", "-"));
        singleProductPage.enterUserNameAndEmail("ann", "ann@gmail.com");
        singleProductPage.addStarRating("star-2");
        singleProductPage.clickCommentSubmitButton();
    }

    @Test
    public void shouldAddProductToCartFromSingleProductCardPage(){
        ShopPage shopPage = homePage.selectShopCategory("Akcesoria");
        SingleProductPage singleProductPage = shopPage.clickProduct(3);
        singleProductPage.clickAddToCardSingeProductButton();
        String singleProductName = singleProductPage.getSingleProductName();
        ShoppingCardPage shoppingCardPage = singleProductPage.clickWidget();
        assertThat(singleProductName.equals(shoppingCardPage.getProductsInBasketNames()));
    }

    @Test
    public void shouldAddProductToCartFromRecommendedProductsSection(){
        ShopPage shopPage = homePage.selectShopCategory("Kobieta");
        SingleProductPage singleProductPage = shopPage.clickProduct(2);
        singleProductPage.clickAddToCardSingeProductButton();
        singleProductPage.clickRecommendedProductsButton(0);
        singleProductPage.clickRecommendedProductsButton(1);
        List recommendedProductsName = singleProductPage.getRecommendedProductsName();
        ShoppingCardPage shoppingCardPage = singleProductPage.clickWidget();
        assertThat(recommendedProductsName.equals(shoppingCardPage.getProductName()));

    }




}
