package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Menu extends PageObject {
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

    public MyAccountPage clickMyAccount() {
        myAccountButton.click();
        return new MyAccountPage(webDriver);
    }

    public WistListPage clickWistListPage() {
        wishListButton.click();
        return new WistListPage(webDriver);

    }

    public AboutPage clickAboutPage() {
        aboutButton.click();
        return new AboutPage(webDriver);
    }

    public ShoppingCard clickShoppingCard() {
        shoppingCardButton.click();
        return new ShoppingCard(webDriver);
    }

    public HomePage clickLogo(){
        logoButton.click();
        return new HomePage(webDriver);
    }

}
