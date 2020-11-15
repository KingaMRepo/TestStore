package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

import static javax.swing.UIManager.get;

public class WishListPage extends Menu {
    @FindBy(tagName = "h1")
    private WebElement header;
    @FindBy(css = "[class='dateadded'] +a[class*='button add_to_cart_button']")
    private List<WebElement> addToCardButton;
    @FindBy(css = "td[class='product-name']>a")
    private List<WebElement> productsNames;
    @FindBy(css = "td >a[class*='remove_from_wishlist']")
    private List<WebElement> productRemoveButtons;
    @FindBy(css="div.woocommerce-message ")
    private WebElement removeAlert;
    @FindBy(css = ".product-name>a")
    private WebElement wishListProduct;




    private Map<String, WebElement>wishListRemoveProducts = new HashMap<>();

    public WishListPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Lista życzeń"));
        for (int i = 0; i <productsNames.size() ; i++) {
            wishListRemoveProducts.put(productsNames.get(i).getText(), productRemoveButtons.get(i));
        }
    }

    public ShoppingCardPage addProductFromWishListToCard(Integer index) {
        addToCardButton.get(index).click();
        return new ShoppingCardPage(webDriver);
    }

    public WishListPage removeProductsByName(String name){
        wishListRemoveProducts.get(name).click();
        return new WishListPage(webDriver);
    }

    public Set<String>getProductName(){
        return wishListRemoveProducts.keySet();
    }

    public Boolean isRemoveAlertDisplay(){
        wait.until(ExpectedConditions.elementToBeClickable(header));
        return removeAlert.isDisplayed();
    }

    public String getWishListProductName() {
        return wishListProduct.getText();

    }
}




