package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Menu extends PageObject {
    @FindBy(id = "menu-item-381")
    private  WebElement homeButton;
    @FindBy(id = "menu-item-829")
    private WebElement contactButton;
    @FindBy(id = "menu-item-45")
    private WebElement shopButton;
    @FindBy(xpath = ".//ul[@class = 'sub-menu']/li/a")
    private List<WebElement> shopSubMenu;
    @FindBy(xpath = ".//li[@id = 'menu-item-825']/a")
    private WebElement myAccountButton;
    @FindBy(xpath = ".//li[@id = 'menu-item-1761']/a")
    private WebElement wishListButton;
    @FindBy(xpath = ".//li[@id = 'menu-item-828']/a")
    private WebElement aboutButton;
    @FindBy(className = "ast-cart-menu-wrap")
    private WebElement shoppingCardButton;
    @FindBy (className = "site-logo-img")
    private WebElement logoButton;
    @FindBy(className = "ast-site-header-cart-li")
    private WebElement widgetButton;
    @FindBy(css ="a[class*='remove_from_cart_button']")
    private List<WebElement>widgetProduct;
    @FindBy(css="div[class='ast-site-header-cart-data'] >div > div>ul > li > a:nth-child(2) ")
    private List<WebElement>widgetProductNames;
    @FindBy(css ="div[class*='widget_shopping_cart']")
    private WebElement widgetCard;
    @FindBy(tagName = "h1")
    public WebElement h1Header;



    public Menu(WebDriver driver) {
        super(driver);
    }

    public ContactPage clickContact() {
        contactButton.click();
        return new ContactPage(webDriver);
    }

    public ShopPage selectShopCategory(String categoryName) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(shopButton).build().perform();

        WebElement selectedCategory = null;
        for (int i = 0; i < shopSubMenu.size(); i++) {
            if (shopSubMenu.get(i).getText().equals(categoryName)) {
                selectedCategory = shopSubMenu.get(i);
            }
        }
        selectedCategory.click();
        return new ShopPage(webDriver);
    }

    public ShopPage clickShop(){
        shopButton.click();
        return new ShopPage(webDriver);
    }

    public HomePage clickHome(){
        homeButton.click();
        return new HomePage(webDriver);
    }

    public MyAccountPage clickMyAccount() {
        myAccountButton.click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage clickMyAccountWaitForOrder() {
        wait.until(ExpectedConditions.textToBePresentInElement(h1Header, "Zamówienie"));
        myAccountButton.click();
        return new MyAccountPage(webDriver);
    }


    public WishListPage clickWistListPage() {
        wishListButton.click();
        return new WishListPage(webDriver);

    }

    public AboutPage clickAboutPage() {
        aboutButton.click();
        return new AboutPage(webDriver);
    }

    public ShoppingCardPage clickShoppingCard() {
        shoppingCardButton.click();
        return new ShoppingCardPage(webDriver);
    }

    public HomePage clickLogo(){
        logoButton.click();
        return new HomePage(webDriver);
    }

    public void showProductsFromWidget() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(widgetButton).build().perform();
    }
    public ShopPage deleteProductFromWidgetByName(String name){
        for (int i = 0; i <widgetProductNames.size(); i++) {
            if(widgetProductNames.get(i).getText().equals(name)){
                wait.until(ExpectedConditions.visibilityOf(widgetCard));
                widgetProduct.get(i).click();
                return new ShopPage(webDriver);
            }
        }
        return new ShopPage(webDriver);

    }
    public HomePage goFromWidgetButtonToShoppingCard(){
        widgetButton.click();
        return new HomePage(webDriver);
    }
}
