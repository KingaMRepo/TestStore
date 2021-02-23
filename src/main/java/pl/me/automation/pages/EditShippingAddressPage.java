package pl.me.automation.pages;

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
    @FindBy(css = ".woocommerce-notices-wrapper>ul")
    private WebElement errorField;
    @FindBy(css = "input#shipping_state")
    private WebElement shippingState;



    public EditShippingAddressPage(WebDriver driver)
    {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Adres do wysyłki"));
    }

    public void clearShippingFormFields(){
        shippingFirstName.clear();
        shippingFirstLastName.clear();
        shippingAddress1.clear();
        shippingPostcode.clear();
        shippingCity.clear();
        }

    public void fillInShippingAddressForm(String name, String lastName, String countryName, String shippingAddress,String city, String shippingProvince, String postalCode){
        shippingFirstName.clear();
        shippingFirstName.sendKeys(name);
        shippingFirstLastName.clear();
        shippingFirstLastName.sendKeys(lastName);
        Select select = new Select(shippingCountrySelect);
        select.selectByVisibleText(countryName);
        shippingAddress1.clear();
        shippingAddress1.sendKeys(shippingAddress);
        shippingCity.clear();
        shippingCity.sendKeys(city);
        shippingState.clear();
        shippingState.sendKeys(shippingProvince);
        shippingPostcode.clear();
        shippingPostcode.sendKeys(postalCode);

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
        wait.until(ExpectedConditions.visibilityOf(errorField));
        List<String>labels = new ArrayList<>();
        for (int i = 0; i <errors.size(); i++) {
            labels.add(errors.get(i).getText());
        }
        return labels;
    }


}
