package pl.me.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class EditBillingAddressPage extends Menu {
    @FindBy(tagName = "h3")
    private WebElement header;
    @FindBy(id = "billing_first_name")
    private WebElement userNameInput;
    @FindBy(id = "billing_last_name")
    private WebElement userLastNameInput;
    @FindBy(id= "billing_company")
    private WebElement companyInput;
    @FindBy(id="billing_country")
    private WebElement countrySelect;
    @FindBy(id = "billing_address_1")
    private WebElement address1Input;
    @FindBy(id="billing_address_2")
    private WebElement address2Input;
    @FindBy(id="billing_city")
    private WebElement cityInput;
    @FindBy(id="billing_state")
    private  WebElement stateInput;
    @FindBy(id="billing_postcode")
    private WebElement postCodeInput;
    @FindBy(id="billing_phone")
    private WebElement phoneNumber;
    @FindBy(name="save_address")
    private WebElement submitButton;
    @FindBy(css="[class~='woocommerce-Address']>[class~='woocommerce-Address-title']+address")
    private WebElement paymentAddress;
    @FindBy(id="billing_state")
    private WebElement billingState;
    @FindBy(css="div.woocommerce-message")
    private WebElement editBillingAddressAlert;
    @FindBy(css=".woocommerce-error>li strong")
    private List<WebElement>errorsStrong;
    @FindBy(css=".woocommerce-error>li")
    private List<WebElement>errors;
    @FindBy(css =".woocommerce-notices-wrapper")
    private WebElement errorsField;



    public EditBillingAddressPage(WebDriver driver)
    {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Adres rozliczeniowy"));
    }



    public MyAccountPage clickBillingSubmitButton() {
        submitButton.click();
        return new MyAccountPage(webDriver);
    }

    public void fillInBillingAddressForm(String name, String lastName, String CountryName, String billingAddress1, String billingCity, String billingRegion, String billingPostCode, String BillingNumber ) {
            userNameInput.clear();
            userNameInput.sendKeys(name);
            userLastNameInput.clear();
            userLastNameInput.sendKeys(lastName);
            Select select = new Select(countrySelect);
            select.selectByVisibleText(CountryName);
            address1Input.clear();
            address1Input.sendKeys(billingAddress1);
            cityInput.clear();
            cityInput.sendKeys(billingCity);
            billingState.clear();
            billingState.sendKeys(billingRegion);
            postCodeInput.clear();
            postCodeInput.sendKeys(billingPostCode);
            phoneNumber.clear();
            phoneNumber.sendKeys(BillingNumber);
        }

    public void clearBillingAddressFormFields() {
        userNameInput.clear();
        userLastNameInput.clear();
        address1Input.clear();
        cityInput.clear();
        //billingState.clear();
        postCodeInput.clear();
        phoneNumber.clear();;
    }

    public String getPaymentAddress(){
        wait.until(ExpectedConditions.visibilityOf(paymentAddress));
        return paymentAddress.getText();
    }

    public Boolean isEditBillingAddressAlertDisplayed(){
        return editBillingAddressAlert.isDisplayed();
    }

    public List<String> getErrorsLabels(){
        List<String>labels = new ArrayList<>();
        for (int i = 0; i <errors.size(); i++) {
            labels.add(errors.get(i).getText());
        }
        return labels;
    }

    public List<String> getErrorsLabelsStrong(){
        List<String>labels = new ArrayList<>();
        for (int i = 0; i <errorsStrong.size(); i++) {
            labels.add(errorsStrong.get(i).getText());
        }
        return labels;
    }


}

