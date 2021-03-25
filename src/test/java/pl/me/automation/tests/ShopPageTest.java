package pl.me.automation.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.me.automation.pages.*;

import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShopPageTest extends BaseTest {

    @Test
    public void shouldFindProductByWritingCorrectPhrase() {
        ShopPage shopPage = homePage.clickShop();
        SearchResultsPage searchResult = shopPage.findProduct(shop.getShopPageFindProduct());
        assertThat(searchResult.isSearchResultsPageDisplayedShowingProduct()).isTrue();
    }

    @Test
    public void shouldFindProductByWritingIncorrectPhrase() {
        ShopPage shopPage = homePage.selectShopCategory(home.getHomeSelectShopCategory1());
        SearchResultsPage searchResult = shopPage.findProduct(shop.getShopPageFindProductIncorrectFrase());
        assertThat(searchResult.isSearchResultsPageDisplayedShowingAlert()).isTrue();
    }

    @Test
    public void shouldSortProductByItsRate() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.sortProducts(shop.getShopPageSortProductsSortByAverageGrade());
        shopPage = new ShopPage(webDriver);
        assertThat(shopPage.getStarRating()).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    public void shouldSortProductByItsPrice() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.sortProducts(shop.getShopPageSortProductsFromLowestPrice());
        shopPage.sortProducts(shop.getShopPageSortProductsFromHighestPrice());
        assertThat(shopPage.getProductPrice()).isSortedAccordingTo(Comparator.reverseOrder());
    }


    @Test
    public void shouldGoFromSearchResultsToProductCardClickOnProductSlidePhotoAndClose() {
        ShopPage shopPage = homePage.clickShop();
        SearchResultsPage searchResult = shopPage.findProduct(shop.getShopPageAddProductsToBasket4());
        SingleProductPage photoZoom = searchResult.displayProductPhoto();
        photoZoom.zoomProductPhoto();
        assertTrue(photoZoom.isZoomImageDisplayed());
    }

    @Test
    public void shouldDeleteChosenFilters() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.filterProductsBySize(shop.getShopPageFilterProductsBySize3());
        shopPage.filterProductsBySize(shop.getShopPageFilterProductsBySize4());
        shopPage.filterProductsByColour(shop.getShopPageFilterProductsByColour());
        shopPage.activeFiltersDeleteByName(shop.getShopPageFilterProductsBySize4());
        List<String> filterLabels = shopPage.getFilterLabels();
        assertThat(filterLabels).containsExactly(shop.getShopPageFilterProductsByColour(), shop.getShopPageFilterProductsBySize3());
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
        shopPage.filterProductsBySize(shop.getShopPageFilterProductsBySize1());
        shopPage.filterProductsBySize(shop.getShopPageFilterProductsBySize2());
        shopPage.activeFiltersDeleteByName(shop.getShopPageFilterProductsBySize1());
        List<String> filterLabels = shopPage.getFilterLabels();
        assertThat(filterLabels).containsExactly(shop.getShopPageFilterProductsBySize2());
    }

    @Test
    public void shouldFilterProductByColour() {
        ShopPage shopPage = homePage.clickShop();
        shopPage.filterProductsByColour(shop.getShopPageFilterProductsByColour2());
        shopPage.activeFiltersDeleteByName(shop.getShopPageActiveFiltersDeleteByName1());
        List<String> filterLabels = shopPage.getFilterLabels();
        assertThat(filterLabels).containsExactly();

    }


}





















