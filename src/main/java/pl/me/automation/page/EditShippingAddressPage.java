package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class EditShippingAddressPage extends Menu {
    @FindBy(tagName = "h3")
    private WebElement header;
    @FindBy(id = "shipping_first_name")
    private WebElement shippingFirstName;
    @FindBy(id = "shipping_last_name")
    private WebElement shippingFirstLastName;
    @FindBy(id = "shipping_country")
    private WebElement shippingCountrySelect;
    @FindBy(id = "shipping_address_1")
    private WebElement shippingAddress1;
    @FindBy(id = "shipping_postcode")
    private WebElement shippingPostcode;
    @FindBy(id = "shipping_city")
    private WebElement shippingCity;
    @FindBy(css = "button[name ='save_address']")
    private WebElement shippingSaveButton;
    @FindBy(css = "div.woocommerce-message")
    private WebElement shippingAddressFormAlert;
    @FindBy(css = "ul.woocommerce-error")
    private WebElement shippingAddressFormErrorAlert;
    @FindBy(css=".woocommerce-error>li")
    private List<WebElement>errors;



    public EditShippingAddressPage(WebDriver driver)
    {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Adres do wysyłki"));
    }

    public void enterShippingUserName(String name) {
        shippingFirstName.clear();
        shippingFirstName.sendKeys(name);
    }
    public void clearShippingUserName() {
        shippingFirstName.clear();
    }

    public void enterShippingUserLastName(String lastName) {
        shippingFirstLastName.clear();
        shippingFirstLastName.sendKeys(lastName);
    }

    public void clearShippingUserLastName() {
        shippingFirstLastName.clear();
    }

    public void selectBillingCountryName(String value){
        Select select = new Select(shippingCountrySelect);
        select.selectByVisibleText(value);
    }

    public void enterShippingAddress1(String billingAddress1) {
        shippingAddress1.clear();
        shippingAddress1.sendKeys(billingAddress1);
    }

    public void clearShippingAddress1() {
        shippingAddress1.clear();
    }


    public void enterShippingPostalCode(String billingAddress1) {
        shippingPostcode.clear();
        shippingPostcode.sendKeys(billingAddress1);
    }

    public void clearShippingPostcode() {
        shippingPostcode.clear();
    }

    public void enterShippingCity(String billingAddress1) {
        shippingCity.clear();
        shippingCity.sendKeys(billingAddress1);
    }

    public void clearShippingCity() {
        shippingCity.clear();
    }


    public MyAccountPage clickShippingSubmitButton() {
        shippingSaveButton.click();
        return new MyAccountPage(webDriver);
    }


    public String getShippingAddressFormAlert() {
        return shippingAddressFormAlert.getText();
    }

    public Boolean isShippingAddressFormAlert() {
        return shippingAddressFormErrorAlert.isDisplayed();
    }

    public List<String> getErrorLabels(){
        List<String>labels = new ArrayList<>();
        for (int i = 0; i <errors.size(); i++) {
            labels.add(errors.get(i).getText());
        }
        return labels;
    }







}
