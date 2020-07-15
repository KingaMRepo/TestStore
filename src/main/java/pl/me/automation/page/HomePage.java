package pl.me.automation.page;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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
    @FindBy(id = "cn-refuse-cookie")
    private WebElement cookieRefuseButton;
    @FindBy(id = "cn-more-info")
    private WebElement cookiePrivacyPolicyButton;
    @FindBy(className = "cookie-notice-container")
    private WebElement cookieNoticeContainer;
    @FindBy(id = ".astra-shop-summary-wrap>.star-rating+a")
    private List<WebElement> recommendedProducts;


    public HomePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(buyButton));
    }

    public void clickCookie() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cookieNotice));
            acceptCookieButton.click();
        } catch (ElementNotInteractableException e) {
            System.err.println("Ciasteczko nie wyświetliło się");

        }
    }

    public ShoppingCardPage addProductToCardAndGoToShoppingCard() {
        addToShoppingCardButton.click();
        wait.until(ExpectedConditions.visibilityOf(checkShoppingCardButton));
        checkShoppingCardButton.click();
        return new ShoppingCardPage(webDriver);
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

    public void acceptCookie() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
        cookieAcceptButton.click();
    }

    public void refuseCookie() {
        cookieRefuseButton.click();
    }

    public HomePage clickPrivacyPolicyButton() {
        cookiePrivacyPolicyButton.click();
        return new HomePage(webDriver);
    }

    public Boolean isCookieAcceptButtonDisplayed() {
        return cookieAcceptButton.isDisplayed();
    }

    public Boolean isCookieRefuseButtonDisplayed() {
        return cookieRefuseButton.isDisplayed();
    }


    public void addRecommendedProductsToBasket(Integer index) {
        recommendedProducts.get(index).click();
    }
}