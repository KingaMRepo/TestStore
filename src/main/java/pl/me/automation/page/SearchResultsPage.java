package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends Menu {

    @FindBy(css=".ast-archive-description>h1")
    private WebElement h1;
    @FindBy(css="div[class~='post-thumb-img-content']")
    private WebElement searchResultProduct;
    @FindBy(css=".page-content>p")
    private WebElement searchResultAlert;
    @FindBy(css="div[class~='post-thumb-img-content']>a")
    private WebElement searchResultImage;
    @FindBy(css = "#woocommerce-product-search-field-0+button")
    private WebElement searchResultButton;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h1, "Search Results for:" ));
    }

    public Boolean isSearchResultsPageDisplayedShowingProduct(){
        return searchResultProduct.isDisplayed();
    }
    public Boolean isSearchResultsPageDisplayedShowingAlert(){
        return searchResultAlert.isDisplayed();
    }
    public SingleProductPage displayProductPhoto() {
        searchResultImage.click();
        return new SingleProductPage(webDriver);
    }

    public ShopPage clickSearchResultButton() {
        searchResultButton.click();
        return new ShopPage(webDriver);
    }

}
