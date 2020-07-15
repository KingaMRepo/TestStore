package pl.me.automation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.page.HomePage;
import pl.me.automation.page.ShopPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopPageTest {
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
       // webDriver.close();
    }
    @Test
    public void ShouldFindProduct(){
        ShopPage shopPage = homePage.clickShop();
        //shopPage.findProduct("jeans");
        //shopPage.filterProductsByPrice();
        //shopPage.filterProductsBySize("XL");
        //shopPage.filterProductsBySize("30");
        //shopPage.filterProductsByColour("Gray");
        //shopPage.sortProducts("Sortuj od najnowszych");
       // shopPage.displayProductPhoto();
        //shopPage.activeFiltersDeleteByIndex(1);
        //shopPage.activeFiltersDeleteByName("30");
        //shopPage.clickAddToBasketButton(1);
        List<String> filterLabels = shopPage.getFilterLabels();
        assertThat(filterLabels).containsExactly("30", "Gray");




    }


















}




