package pl.me.automation.page;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.me.automation.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;

public class HomePage extends Menu {

    @FindBy(xpath = "//div[@data-id='523ed945']/div/div/a")
    private WebElement buyButton;
    @FindBy(id = "cn-accept-cookie")
    private WebElement acceptCookieButton;
    @FindBy(css = "div[class='star-rating']+a[aria-label='Dodaj “DNK Blue Sport Shoes” do koszyka']")
    private WebElement addToShoppingCardButton;
    @FindBy(css = "a[aria-label='Dodaj “DNK Blue Sport Shoes” do koszyka']+a[class*='added_to_cart']")
    private WebElement checkShoppingCardButton;
    @FindBy(css = ".yith-wcwl-add-button>a")
    private List<WebElement> wishListButtons;
    @FindBy(id = "yith-wcwl-popup-message")
    private WebElement wishListPopupMessage;
    @FindBy(id = "wpforms-confirmation-32")
    private WebElement contactMessage;
    @FindBy(id = "cookie-notice")
    private WebElement cookieNotice;
    @FindBy(id = "cn-accept-cookie")
    private WebElement cookieAcceptButton;
    @FindBy(id = "cn-more-info")
    private WebElement cookiePrivacyPolicyButton;
    @FindBy(className = "cookie-notice-container")
    private WebElement cookieNoticeContainer;
    @FindBy(css = ".astra-shop-summary-wrap>.star-rating+a")
    private List<WebElement> recommendedProducts;
    @FindBy(css = ".astra-shop-summary-wrap>a>h2")
    private List<WebElement> recommendedProductsNames;


    private Map<String, Product> products = new HashMap<>();
    private List<Product> productsInBasket = new ArrayList<>();


    public HomePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(buyButton));
        for (int i = 0; i < recommendedProductsNames.size(); i++) {
            products.put(recommendedProductsNames.get(i).getText(), new Product(recommendedProducts.get(i), null, 0, recommendedProductsNames.get(i).getText()));
        }
    }

    public void clickCookie() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton));
            acceptCookieButton.click();
        } catch (ElementNotInteractableException e) {
            System.err.println("Ciasteczko nie wyświetliło się");
        }
    }

    public void addProductsToWishList(Integer index) {
        Actions actions = new Actions(webDriver);
        WebElement webElement = wishListButtons.get(index);
        actions.moveToElement(webElement).click().build().perform();
    }

    public Boolean isWishListPopupMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(wishListPopupMessage));
        return wishListPopupMessage.isDisplayed();
    }


    public void addRecommendedProductsToBasket(String name) {
        products.get(name).getButton().click();
        productsInBasket.add(products.get(name));
    }

    public List<Product> getProductsInBasket() {
        return productsInBasket;
    }

}