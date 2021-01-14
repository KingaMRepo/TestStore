package pl.me.automation.page;

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
    @FindBy(css=".woocommerce-error>li")
    private List<WebElement>errors;
    @FindBy(css =".woocommerce-notices-wrapper")
    private WebElement errorsField;



    public EditBillingAddressPage(WebDriver driver)
    {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Adres rozliczeniowy"));
    }


    public void enterBillingUserName(String name) {
        userNameInput.clear();
        userNameInput.sendKeys(name);
    }
    public void clearBillingUserName() {
        userNameInput.clear();
    }

    public void enterBillingUserLastName(String lastName) {
        userLastNameInput.clear();
        userLastNameInput.sendKeys(lastName);
    }

    public void clearBillingUserLastName() {
        userLastNameInput.clear();
    }


    public void selectBillingCountryName(String value){
        Select select = new Select(countrySelect);
        select.selectByVisibleText(value);
    }

    public void enterBillingAddress1(String billingAddress1) {
        address1Input.clear();
        address1Input.sendKeys(billingAddress1);
    }

    public void clearBillingAddress1() {
        address1Input.clear();
    }


    public void enterBillingCity(String billingCity) {
        cityInput.clear();
        cityInput.sendKeys(billingCity);
    }

    public void clearBillingCity() {
        cityInput.clear();
    }

    public void enterBillingRegion(String billingRegion) {
        billingState.clear();
        billingState.sendKeys(billingRegion);
    }

    public void clearBillingRegion() {
        billingState.clear();
    }

    public void enterBillingPostCode(String billingPostCode) {
        postCodeInput.clear();
        postCodeInput.sendKeys(billingPostCode);
    }

    public void clearBillingPostCode() {
        postCodeInput.clear();
    }

    public void enterBillingPhoneNumber(String BillingNumber) {
        phoneNumber.clear();
        phoneNumber.sendKeys(BillingNumber);
    }

    public void clearBillingPhoneNumber() {
        phoneNumber.clear();
    }

    public MyAccountPage clickBillingSubmitButton() {
        submitButton.click();
        return new MyAccountPage(webDriver);
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


}

