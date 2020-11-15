package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.*;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopPageTest {
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
    public void shouldFindProductByWritingCorrectPhrase() {
        ShopPage shopPage = homePage.clickShop();
        SearchResultsPage searchResult = shopPage.findProduct("jeans");
        assertThat(searchResult.isSearchResultsPageDisplayedShowingProduct()).isTrue();
    }

    @Test
    public void shouldFindProductByWritingIncorrectPhrase() {
        ShopPage shopPage = homePage.selectShopCategory("Kobieta");
        SearchResultsPage searchResult = shopPage.findProduct("aaajeanns");
        assertThat(searchResult.isSearchResultsPageDisplayedShowingAlert()).isTrue();
    }

    @Test
    public void shouldSortProductByItsRate() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.sortProducts("Sortuj wg średniej oceny");
        shopPage = new ShopPage(webDriver);
        assertThat(shopPage.getStarRating()).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    public void shouldSortProductByItsPrice() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.sortProducts("Sortuj wg ceny: od najniższej");
        shopPage.sortProducts("Sortuj wg ceny: od najwyższej");
        assertThat(shopPage.getProductPrice()).isSortedAccordingTo(Comparator.reverseOrder());
    }


    @Test
    public void shouldGoFromSearchResultsToProductCardClickingOnProductSlidePhotoAndClose() {
        ShopPage shopPage = homePage.clickShop();
        SearchResultsPage searchResult = shopPage.findProduct("Aqua Wind Breaker");
        SingleProductPage photoZoom = searchResult.displayProductPhoto();
        photoZoom.ZoomProductPhoto();
        assertTrue(photoZoom.isZoomImageDisplayed());
    }

    @Test
    public void shouldDeleteChosenFilters() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.filterProductsBySize("XL");
        shopPage.filterProductsBySize("30");
        shopPage.filterProductsByColour("Gray");
        shopPage.activeFiltersDeleteByName("30");
        List<String> filterLabels = shopPage.getFilterLabels();
        assertThat(filterLabels).containsExactly("Gray", "XL");
    }


    @Test
    public void shouldFilterPriceUsingDragAndDropSlider() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.dragAndDropPriceFilter();
        assertThat(shopPage.getDragAndDropSliderMinValue().equals(shopPage.getDragAndDropSliderMinFilter()));
        assertThat(shopPage.getDragAndDropSliderMaxValue().equals(shopPage.getDragAndDropSliderMaxFilter()));

    }

    @Test
    public void shouldFilterProductBySize() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.filterProductsBySize("36");
        shopPage.filterProductsBySize("34");
        shopPage.activeFiltersDeleteByName("36");
        List<String> filterLabels = shopPage.getFilterLabels();
        assertThat(filterLabels).containsExactly("34");

    }

    @Test
    public void shouldFilterProductByColour() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.filterProductsByColour("Blue");
        shopPage.activeFiltersDeleteByName("Blue");
        List<String> filterLabels = shopPage.getFilterLabels();
        assertThat(filterLabels).containsExactly();

    }


}





















