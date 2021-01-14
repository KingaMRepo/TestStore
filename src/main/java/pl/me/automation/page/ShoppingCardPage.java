package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.me.automation.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCardPage extends Menu {


    @FindBy(xpath = "//div[@class ='elementor-widget-container']")
    private WebElement h1Header;
    @FindBy(xpath = "//input[@type='radio']/following-sibling::label")
    private List<WebElement> deliveryRadioButtonsLabels;
    @FindBy(xpath = "//td[@class ='product-name']")
    private List<WebElement> productFromBasket;
    @FindBy(css = ".product-remove>a")
    private List<WebElement> removeProductFromBasket;
    @FindBy(className = "cart-empty")
    private WebElement emptyBasketLabel;
    @FindBy(className = "cart-subtotal")
    private WebElement basketTotalPrice;
    @FindBy(className = "wc-proceed-to-checkout")
    private WebElement proceedToCheckoutButton;
    @FindBy(css = ".product-price>span")
    private WebElement productUnitPrice;
    @FindBy(css = ".product-name>a")
    private WebElement productName;
    @FindBy(css = ".product-name>a")
    private List<WebElement> productsInBasketNames;
    @FindBy(id = "coupon_code")
    private WebElement couponCodeInput;
    @FindBy(css = ".coupon>button")
    private WebElement applyCouponButton;
    @FindBy(css = "div.woocommerce-message")
    private WebElement appliedCouponAlertMessage;
    @FindBy(css = "[class='cart-subtotal']>td>[class~='woocommerce-Price-amount']")
    private WebElement priceAmount;
    @FindBy(css = "[class~='cart-discount']>td>[class~='woocommerce-Price-amount']")
    private WebElement couponAmount;
    @FindBy(css = "#shipping_method>li:first-child>input +label>span")
    private WebElement shippingPriceFirstMethod;
    @FindBy(css = "#shipping_method>li:nth-child(2)>input+label>span")
    private WebElement shippingPriceSecondMethod;
    @FindBy(css = "#shipping_method>li:nth-child(3)>input+label>span")
    private WebElement shippingPriceThirdMethod;
    @FindBy(css = ".order-total>td>strong>span")
    private WebElement sumPrice;
    @FindBy(css = "ul.woocommerce-error>li")
    private WebElement couponErrorAlert;
    @FindBy(css = "#shipping_method>li>input")
    private List<WebElement> shippingMethods;


    private Map<String, Product> products = new HashMap<>();

    public ShoppingCardPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h1Header, "Koszyk"));
        wait.until(ExpectedConditions.elementToBeClickable(h1Header));
        for (int i = 0; i < productFromBasket.size(); i++) {
            products.put(productFromBasket.get(i).getText(), new Product(removeProductFromBasket.get(i), null, null, productFromBasket.get(i).getText()));
        }
    }


    public List<String> getProductName() {
        return productFromBasket.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    public void removeProductsByName(String name) {
        products.get(name).getButton().click();
    }

    public Boolean isDisplayEmptyBasket() {
        return emptyBasketLabel.isDisplayed();
    }

    public Double getShoppingCardTotalPrice() {
        return Double.valueOf(basketTotalPrice.getText().replace("Kwota zł", ""));
    }

    public Double getProductPrice(String name) {
        return Double.valueOf(products.get(name).getPrice().replace("zł", ""));
    }

    public Double getSummaryPrice(Integer productQuantity) {
        return Double.valueOf(productUnitPrice.getText().replace("zł", "")) * productQuantity;
    }

    public List<String> getProductsInBasketNames() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < productsInBasketNames.size(); i++) {
            names.add(productsInBasketNames.get(i).getText());
        }
        return names;
    }

    public String applyCouponCode(String couponCode) {
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponButton));
        couponCodeInput.clear();
        couponCodeInput.sendKeys(couponCode);
        applyCouponButton.click();

        return couponCode;
    }

    public String getAppliedCouponSuccessfullyAlertMessage() {
        wait.until(ExpectedConditions.visibilityOf(appliedCouponAlertMessage));
        return appliedCouponAlertMessage.getText();
    }

    public String getAppliedCouponFailAlertMessage() {
        wait.until(ExpectedConditions.visibilityOf(couponErrorAlert));
        return couponErrorAlert.getText();
    }

    public Double getProductPriceAmount() {
        wait.until(ExpectedConditions.visibilityOf(priceAmount));
        return Double.valueOf(priceAmount.getText().replace("zł", ""));
    }

    public Double getProductCouponAmount() {
        return Double.valueOf(couponAmount.getText().replace("zł", ""));
    }

    public Double getProductShippingFirstMethodPrice() {
        return Double.valueOf(shippingPriceFirstMethod.getText().replace("zł", ""));
    }

    public Double getProductShippingSecondMethodPrice() {
        return Double.valueOf(shippingPriceSecondMethod.getText().replace("zł", ""));
    }

    public Double getProductShippingThirdMethodPrice() {
        return Double.valueOf(shippingPriceThirdMethod.getText().replace("zł", ""));
    }

    public Double getProductSumPrice() {
        return Double.valueOf(sumPrice.getText().replace("zł", ""));
    }

    public ShoppingCardPage chooseShippingMethod(Integer index) {
        for (int i = 0; i < shippingMethods.size(); i++) {
            shippingMethods.get(index).click();
        }
        return new ShoppingCardPage(webDriver);
    }

    public List<String> getDeliveryType() {
        return deliveryRadioButtonsLabels.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    //        List<String>list = new ArrayList<>();
//        for (int i = 0; i <deliveryRadioButtonsLabels.size() ; i++) {
//            list.add(deliveryRadioButtonsLabels.get(i).getText());
//        }
//        return list;

    public PaymentPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new PaymentPage(webDriver);
    }


}


















