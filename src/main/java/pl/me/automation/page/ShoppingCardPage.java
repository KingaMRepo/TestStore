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

public class ShoppingCardPage extends Menu{

    @FindBy(xpath = "//div[@class ='elementor-widget-container']")
    private WebElement h1Header;
    @FindBy(xpath = "//input[@type='radio']/following-sibling::label")
    private List<WebElement> deliveryRadioButtonsLabels;
    @FindBy(xpath = "//td[@class ='product-name']")
    private List<WebElement> productFromBasket;
    @FindBy(className = "product-remove")
    private List<WebElement> removeProductFromBasket;
    @FindBy(className = "cart-empty")
    private WebElement emptyBasketLabel;
    @FindBy(className = "cart-subtotal")
    private WebElement basketTotalPrice;
    @FindBy(className = "wc-proceed-to-checkout")
    private WebElement proceedToCheckoutButton;

    private Map<String, Product> products = new HashMap<>();

    public ShoppingCardPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h1Header, "Koszyk"));
        for (int i = 0; i <productFromBasket.size() ; i++) {
            products.put(productFromBasket.get(i).getText(),new Product(removeProductFromBasket.get(i), null, null));
        }
    }

    public List<String> getDeliveryType(){
        List<String>list = new ArrayList<>();
        for (int i = 0; i <deliveryRadioButtonsLabels.size() ; i++) {
            list.add(deliveryRadioButtonsLabels.get(i).getText());
        }
        return list;
    }


    public List<String> getProductName() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < productFromBasket.size(); i++) {
            list.add(productFromBasket.get(i).getText());
        }
        return list;
    }

    public void removeProductsByName(String name){
        products.get(name).getButton().click();
    }

   public Boolean isDisplayEmptyBasket(){
        return emptyBasketLabel.isDisplayed();
   }

    public Double getShoppingCardTotalPrice(){
        return Double.valueOf(basketTotalPrice.getText().replace("Kwota zł", ""));
    }

    public PaymentPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new PaymentPage(webDriver);
    }

    public Double getProductPrice(String name){
        return Double.valueOf(products.get(name).getPrice().replace("zł", ""));
    }



}
