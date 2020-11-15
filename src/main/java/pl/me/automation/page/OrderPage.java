package pl.me.automation.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends Menu {

    @FindBy(tagName = "h1")
    public WebElement h1Header;
    @FindBy(css = ".woocommerce-order>p")
    public WebElement receivedOrderAlert;
    @FindBy(css = "li[class~= 'woocommerce-order-overview__order']>strong")
    public WebElement orderNumber;
    @FindBy(css = "[class~='woocommerce-table__product-name']>a")
    private WebElement wishListProductName;


    public OrderPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h1Header, "Zamówienie"));
    }


    public Boolean isReceivedOrderAlertDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(receivedOrderAlert));
        return receivedOrderAlert.isDisplayed();
    }

    public String getOrderNumber() {
        wait.until(ExpectedConditions.visibilityOf(orderNumber));
        return orderNumber.getText();
    }


    public Boolean isOrderAcceptMessageIsDisplay() {
        wait.until(ExpectedConditions.textToBePresentInElement(receivedOrderAlert, "Dziękujemy. Otrzymaliśmy Twoje zamówienie."));
        return receivedOrderAlert.isDisplayed();
    }

    public String getWishListProductName() {
        wait.until(ExpectedConditions.visibilityOf(orderNumber));
        return wishListProductName.getText();
    }

}
