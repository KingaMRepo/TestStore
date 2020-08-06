package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pl.me.automation.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopPage extends Menu {
    @FindBy(xpath = "//div[@id='search-3']/h2[@class='widget-title' ]")
    private WebElement h2Header;
    @FindBy(name = "s")
    private WebElement searchField;
    @FindBy(className = "search-submit")
    private WebElement searchSubmit;
    @FindBy(xpath = "//button[@class='button']")
    private WebElement filterButton;
    @FindBy(css = "[class~='dropdown_layered_nav_size']")
    private WebElement sizeFilterSelect;
    @FindBy(className = "woocommerce-widget-layered-nav-dropdown__submit")
    private WebElement submitButton;
    @FindBy(xpath = "//li[contains(@class, 'chosen-size')]")
    private WebElement activeFilterButton;
    private List<WebElement> activeFiltersButtons;
    @FindBy(xpath = "//select[contains(@class,'dropdown_layered_nav_color')]")
    private WebElement colourFilterInput;
    @FindBy(xpath = "//select[@class='orderby']")
    private WebElement sortingSelect;
    @FindBy(xpath = "//a[@aria-label='Usuń filtr']")
    private List<WebElement> activeFilters;
    @FindBy(xpath = "//div[@class='star-rating']/following-sibling::a[1]")
    private List<WebElement> addToCardButtons;
    @FindBy(css = ".select2-search__field")
    private WebElement sizeFilterInput;
    @FindBy(css = ".woocommerce-loop-product__title")
    private List<WebElement> productNames;
    @FindBy(xpath = "//div[@class='star-rating']/following-sibling::a")
    private List<WebElement>productButton;
    @FindBy(xpath = "//h2/following-sibling::div/p/a[@class='button wc-forward']")
    private WebElement basketButton;
    @FindBy(className = "yith-wcwl-add-button")
    private List<WebElement>wishListButtons;
    @FindBy(css=".price>:first-child")
    private List<WebElement>productPrices;
    @FindBy(css = "ul +p[class *='woocommerce-mini-cart__total']")
    private WebElement widgetCardTotalPrice;
    @FindBy(css="astra-shop-thumbnail-wrap")
    private List<WebElement> productsImageList;
    private List<WebElement>filterNames;
    @FindBy(css="[class~='woocommerce-Price-amount']")
    private List<WebElement>filterPrices;
    @FindBy(css="ul[class ~= 'columns-4']>li:first-child")
    private WebElement firstRateSortedProduct;
    @FindBy(xpath="//div[@class='star-rating']/span")
    private  List<WebElement>starRating;
    @FindBy(css=".price>span")
    private  List<WebElement>productPrice;



    private Map<String, Product> products = new HashMap<>();
    private List<Product>productsInBasket = new ArrayList<>();
    List<String> priceList = new ArrayList<>();


    public ShopPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h2Header, "Szukaj produktu"));
        wait.until(ExpectedConditions.elementToBeClickable(h2Header));
        for (int i = 0; i <productNames.size(); i++) {
            products.put(productNames.get(i).getText(), new Product(productButton.get(i), productPrices.get(i).getText(), 0, productNames.get(i).getText()));
        }

    }
    public void send(String text) {
        searchField.clear();
        searchField.sendKeys(text);

    }

    public SearchResultsPage findProduct(String text) {
        searchField.clear();
        searchField.sendKeys(text);
        searchSubmit.click();
        return new SearchResultsPage(webDriver);
    }


    public void filterProductsByPrice() {
        filterButton.click();
    }

    public void filterProductsBySize(String value) {
        Select select = new Select(sizeFilterSelect);
        select.selectByVisibleText(value);
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.submit();
    }

    public void filterProductsByColour(String value) {
        Select select = new Select(colourFilterInput);
        select.selectByVisibleText(value);

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

    public void enterSize(String text){
        sizeFilterInput.clear();
        sizeFilterInput.sendKeys(text);
       // Actions actions = new Actions(webDriver);
        //actions.keyDown(sizeFilterInput, Keys.ENTER).keyUp(Keys.ENTER).build().perform();
    }

    public List<Product> getProductsInBasket() {
        return productsInBasket;
    }

    public void addProductsToBasket(String name){
       Product product = products.get(name);
       product.getButton().click();
       product.setQuantity(product.getQuantity()+1);
       productsInBasket.add(product);
   }

    public ShoppingCardPage clickBasket(){
       Actions action = new Actions(webDriver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        action.moveToElement(basketButton).build().perform();
        wait.until(ExpectedConditions.visibilityOf(basketButton));
        basketButton.click();

        return new ShoppingCardPage(webDriver);
    }

    public void addProductsToWishList(Integer index)
    {
        wishListButtons.get(index).click();
    }

    public Double getProductPrice(String name){
        return Double.valueOf(products.get(name).getPrice().replace("zł", ""));
    }

    public String getWidgetTotalPrice() {
        return widgetCardTotalPrice.getText();
    }

    public void clickToShowSingleProductCard(Integer index){
        productsImageList.get(index).click();

    }

    public List<String> getFilteredPrices() {
        List<String> priceList = new ArrayList<>();
        for (int i = 0; i < filterPrices.size(); i++) {
            priceList.add(filterPrices.get(i).getText());
        }
        return priceList;
    }

    public List<Double> getStarRating(){
        List<Double>rating = new ArrayList<>();
        for (int i = 0; i <starRating.size() ; i++) {
            String ratingNumber = starRating.get(i).getText();
            System.out.println(ratingNumber);
            if(ratingNumber != null && !ratingNumber.isEmpty()){
                rating.add(Double.parseDouble(ratingNumber.replaceAll("\\D+", "")));
            }
        }
        return rating;
    }

    public List<String>getProductPrice(){
        List<String>price = new ArrayList<>();
        for (int i = 0; i <productPrice.size() ; i++) {
            price.add(productPrice.get(i).getText());
        }
        return price;


    }


}

