package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ShopPage extends Menu {
    @FindBy(xpath = "//div[@id='search-3']/h2[@class='widget-title' ]")
    private WebElement h2Header;
    @FindBy(name = "s")
    private WebElement searchField;
    @FindBy(className = "search-submit")
    private WebElement searchSubmit;
    @FindBy(xpath = "//button[@class='button']")
    private WebElement filterButton;
    @FindBy(xpath = "//select[contains(@class, 'dropdown_layered_nav_size select2-hidden-accessible')]")
    private WebElement sizeFilterInput;
    @FindBy(className = "woocommerce-widget-layered-nav-dropdown__submit")
    private WebElement submitButton;
    @FindBy(xpath = "//li[contains(@class, 'chosen-size')]")
    private WebElement activeFilterButton;
    private List<WebElement> activeFiltersButtons;
    @FindBy(xpath = "//select[contains(@class,'dropdown_layered_nav_color')]")
    private WebElement colourFilterInput;
    @FindBy(xpath = "//select[@class='orderby']")
    private WebElement sortingSelect;
    @FindBy(xpath = "//div[@class='astra-shop-thumbnail-wrap']/a")
    private WebElement productPhoto;
    @FindBy(className = "woocommerce-product-gallery__trigger")
    private WebElement productZoom;
    @FindBy(xpath = "//button[contains(@class, 'button--arrow--right')]")
    private WebElement productbuttonArraw;
    @FindBy(xpath = "//button[contains(@class, 'button--close')]")
    private WebElement closeButton;
    @FindBy(xpath = "//a[@aria-label='Usuń filtr']")
    private List<WebElement> activeFilters;
    @FindBy(xpath = "//div[@class='star-rating']/following-sibling::a[1]")
    private List<WebElement> addToCardButtons;
    @FindBy(css = "[class~='select2-search--inline']")
    private WebElement sendkey;

    public ShopPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h2Header, "Szukaj produktu"));
    }
    public void send(String text) {
        searchField.clear();
        searchField.sendKeys(text);

    }

    public void findProduct(String text) {
        searchField.clear();
        searchField.sendKeys(text);
        searchSubmit.click();
    }

    public void filterProductsByPrice() {
        filterButton.click();
    }

    public void filterProductsBySize(String value) {
        Select select = new Select(sizeFilterInput);
        select.selectByVisibleText(value);
        submitButton.submit();
    }


    public void filterProductsByColour(String value) {
        Select select = new Select(colourFilterInput);
        select.selectByVisibleText(value);
        submitButton.submit();
    }

    public void sortProducts(String value) {
        Select select = new Select(sortingSelect);
        select.selectByVisibleText(value);
        submitButton.submit();
    }

    public void activeFilterDelete() {
        activeFilterButton.click();
    }

    public void activeFiltersDeleteByIndex(Integer index) {
        activeFilters.get(index).click();
    }

    public void activeFiltersDeleteByName(String name) {
        for (int i = 0; i < activeFilters.size(); i++) {
            if (activeFilters.get(i).getText().equals(name)) {
                activeFilters.get(i).click();
                return;
            }

        }
    }

    public void displayProductPhoto() {
        productPhoto.click();
        wait.until(ExpectedConditions.elementToBeClickable(productZoom));
        productZoom.click();
        productbuttonArraw.click();
        closeButton.click();
    }

    public void clickAddToBasketButton(Integer index) {
        addToCardButtons.get(index).click();
    }

    public List<String> getFilterLabels() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < activeFilters.size(); i++) {
            list.add(activeFilters.get(i).getText());
        }
        return list;
    }

}
