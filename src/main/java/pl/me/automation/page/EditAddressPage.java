package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class EditAddressPage extends Menu {
    @FindBy(tagName = "h3")
    private WebElement header;
    @FindBy(xpath = ".//a[contains(@href, 'rozliczeniowy')]")
    private WebElement addressAndBillingEditButton;
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

    public EditAddressPage(WebDriver driver, String headerText) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, headerText));
    }
    public MyAccountPage clickAddressAndBillingEditButton(){
        addressAndBillingEditButton.click();
        return new MyAccountPage(webDriver);
    }
    public void enterBillingUserName(String name) {
        userNameInput.clear();
        userNameInput.sendKeys(name);
    }

    public void enterBillingUserLastName(String lastName) {
        userLastNameInput.clear();
        userLastNameInput.sendKeys(lastName);
    }

    public void enterOptionalBillingCountry(String billingCountry) {
        companyInput.clear();
        companyInput.sendKeys(billingCountry);
    }

    public void selectBillingCountryName(String value){
        Select select = new Select(countrySelect);
        select.selectByVisibleText(value);
    }

    public void enterBillingAddress1(String billingAddress1) {
        address1Input.clear();
        address1Input.sendKeys(billingAddress1);
    }

    public void enterBillingAddress2(String billingAddress2) {
        address2Input.clear();
        address2Input.sendKeys(billingAddress2);
    }

    public void enterBillingCity(String billingCity) {
        cityInput.clear();
        cityInput.sendKeys(billingCity);
    }

    public void enterBillingPostCode(String billingPostCode) {
        postCodeInput.clear();
        postCodeInput.sendKeys(billingPostCode);
    }

    public void enterBillingPhoneNumber(String BillingNumber) {
        phoneNumber.clear();
        phoneNumber.sendKeys(BillingNumber);
    }
    public MyAccountPage clickBillingSubmitButton() {
        submitButton.click();
        return new MyAccountPage(webDriver);
    }


}

