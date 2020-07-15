package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;

public class PaymentPage extends Menu {

    @FindBy(css = "div.woocommerce-billing-fields>h3")
    private WebElement h3Header;
    @FindBy(css = "input[id ='billing_first_name']")
    private WebElement paymentUserNameInput;
    @FindBy(css = "input[id='billing_last_name']")
    private WebElement paymentUserLastNameInput;
    @FindBy(id = "billing_country")
    private WebElement paymentCountrySelect;
    @FindBy(id = "billing_address_1")
    private WebElement paymentAddress;
    @FindBy(id = "billing_city")
    private WebElement paymentCity;
    @FindBy(id = "billing_state")
    private WebElement paymentRegion;
    @FindBy(id = "billing_postcode")
    private WebElement paymentPostCode;
    @FindBy(id = "billing_phone")
    private WebElement paymentPhone;
    @FindBy(id = "billing_email")
    private WebElement paymentEmail;
    @FindBy(id = "account_username")
    private WebElement paymentAccountUsername;
    @FindBy(id = "account_password")
    private WebElement paymentPassword;
    @FindBy(id = "ship-to-different-address-checkbox")
    private WebElement shipToDifferentAddressCheckbox;
    @FindBy(css = "div[class*='woocommerce-NoticeGroup-checkout']")
    private WebElement billingRegistrationError;
    @FindBy(id = "terms")
    private WebElement termsAndConditionsCheckbox;
    @FindBy(css = "[class~='woocommerce-form__label-for-checkbox']")
    private WebElement termsAndConditionsCheckbox1;
    @FindBy(id = "place_order")
    private WebElement paymentSubmitButton;
    @FindBy(id = "account_password")
    private WebElement paymentFormError;
    @FindBy(css = ".woocommerce-error>li")
    private WebElement paymentFormEmailError;
    @FindBy(css = "#shipping_method_0_flat_rate5+label")
    private WebElement shippingRate;
    @FindBy(className = "showcoupon")
    private WebElement clickToAddCouponButton;
    @FindBy(id = "coupon_code")
    private WebElement couponCodeInput;
    @FindBy(css = "[class~='form-row-last']>button")
    private WebElement applyCouponButton;
    @FindBy(css = "div.woocommerce-message")
    private WebElement appliedCouponAlertMessage;
    @FindBy(id = "shipping_first_name")
    private WebElement shippingFirstName;
    @FindBy(id = "shipping_last_name")
    private WebElement shippingLastName;
    @FindBy(id = "shipping_address_1")
    private WebElement shippingAddress;
    @FindBy(id = "shipping_postcode")
    private WebElement shippingPostcode;
    @FindBy(id = "shipping_city")
    private WebElement shippingCity;
    @FindBy(id = "shipping_state")
    private WebElement shippingState;
    @FindBy(id = "shipping_country")
    private WebElement shippingCountrySelect;
    @FindBy(css = "[class='cart-subtotal']>td>[class~='woocommerce-Price-amount']")
    private WebElement priceAmount;
    @FindBy(css = "[class~='cart-discount']>td>[class~='woocommerce-Price-amount']")
    private WebElement couponAmount;
    @FindBy(xpath = "//input[@checked='checked']/following-sibling::label/span[contains(@class, 'woocommerce-Price-amount')] ")
    private WebElement shippingPrice;
    @FindBy(css = ".order-total>td>strong>[class~='woocommerce-Price-amount']")
    private WebElement sumPrice;
    @FindBy(css = "div[class='woocommerce-info']>a")
    private WebElement accountLoginButton;
    @FindBy(id = "username")
    private WebElement userNameOrEmailField;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(css = "button[class~='woocommerce-form-login__submit']")
    private WebElement loginSubmitButton;
    @FindBy(xpath = "//ul[@class='woocommerce-error']/li/strong")
    private WebElement error;
    @FindBy(css = "li>strong")
    private List<WebElement> errors;
    @FindBy(css = ".woocommerce-error>li>strong")
    private WebElement formValidationError;
    @FindBy(css=".lost_password>a")
    private WebElement lostPasswordButton;



    private Object driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h3Header, "Dane płatności"));
    }

//Payment details form
    public void enterBillingUserName(String name) {
        paymentUserNameInput.clear();
        paymentUserNameInput.sendKeys(name);
    }

    public void enterBillingUserLastName(String name) {
        paymentUserLastNameInput.clear();
        paymentUserLastNameInput.sendKeys(name);
    }

    public void selectBillingCountry(Integer index) {
        Select select = new Select(paymentCountrySelect);
        select.selectByIndex(index);
    }

    public void enterBillingUserAddress(String name) {
        paymentAddress.clear();
        paymentAddress.sendKeys(name);
    }

    public void enterBillingUserCity(String city) {
        paymentCity.clear();
        paymentCity.sendKeys(city);
    }

    public void enterBillingUserRegion(String region) {
        paymentRegion.clear();
        paymentRegion.sendKeys(region);
    }

    public void enterBillingUserPostCode(String postCode) {
        paymentPostCode.clear();
        paymentPostCode.sendKeys(postCode);
    }

    public void enterBillingUserPhone(String phone) {
        paymentPhone.clear();
        paymentPhone.sendKeys(phone);
    }

    public void enterBillingUserEmail(String email) {
        paymentEmail.clear();
        paymentEmail.sendKeys(email);
    }

    public void enterBillingAccountUsername(String name) {
        paymentAccountUsername.clear();
        paymentAccountUsername.sendKeys(name);
    }

    public void enterBillingAccountPassword(String password) {
        paymentPassword.clear();
        paymentPassword.sendKeys(password);
    }
    // end of payment details form

    public void deselectShipToDifferentAddressCheckbox() {
        shipToDifferentAddressCheckbox.click();
    }


    public Boolean isBillingRegistrationError() {
        return billingRegistrationError.isDisplayed();
    }

    public void acceptTermsAndConditionsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsCheckbox));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(termsAndConditionsCheckbox).build().perform();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        termsAndConditionsCheckbox.click();
    }

    public OrderPage paymentAccept() {
        //wait.until(ExpectedConditions.elementToBeClickable(paymentSubmitButton));
        paymentSubmitButton.click();
        return new OrderPage(webDriver);
    }

    public Boolean isPaymentEmptyFormErrorDisplayed() {
        return paymentFormError.isDisplayed();
    }

    public Boolean isPaymentFormErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(paymentFormEmailError));
        return paymentFormEmailError.isDisplayed();
    }

    public String getShippingMethodRate() {
        wait.until(ExpectedConditions.visibilityOf(shippingRate));
        return shippingRate.getText();
    }

    public void addCouponCode(String couponCode) {
        clickToAddCouponButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(couponCodeInput));
        couponCodeInput.clear();
        couponCodeInput.sendKeys(couponCode);
        applyCouponButton.click();
    }

    public Boolean isAppliedCouponAlertMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(appliedCouponAlertMessage));
        return appliedCouponAlertMessage.isDisplayed();
    }

//Choosing other shipping address
    public void enterShippingUserName(String name) {
        shippingFirstName.clear();
        shippingFirstName.sendKeys(name);
    }

    public void enterShippingUserLastName(String address) {
        shippingLastName.clear();
        shippingLastName.sendKeys(address);
    }

    public void selectShippingCountry(Integer index) {
        Select select = new Select(shippingCountrySelect);
        select.selectByIndex(index);
    }

    public void enterShippingAddress(String address) {
        shippingAddress.clear();
        shippingAddress.sendKeys(address);
    }

    public void enterShippingRegion(String shippingRegion) {
        shippingState.clear();
        shippingState.sendKeys(shippingRegion);
    }

    public void enterShippingPostCode(String postCode) {
        shippingPostcode.clear();
        shippingPostcode.sendKeys(postCode);
    }

    public void enterShippingCity(String city) {
        shippingCity.clear();
        shippingCity.sendKeys(city);
    }
// End of payment details form

    public Double getProductPriceAmount() {
        wait.until(ExpectedConditions.visibilityOf(priceAmount));
        return Double.valueOf(priceAmount.getText().replace("zł", ""));
    }

    public Double getProductCouponAmount() {
        return Double.valueOf(couponAmount.getText().replace("zł", ""));
    }

    public Double getProductShipping() {
        return Double.valueOf(shippingPrice.getText().replace("zł", ""));
    }

    public Double getProductSumPrice() {
        return Double.valueOf(sumPrice.getText().replace("zł", ""));
    }

    public void clickLoginButton() {
        accountLoginButton.click();
    }

    //Login form for registered user
    public MyAccountPage enterUserNameOrEmail(String username) {
        userNameOrEmailField.clear();
        userNameOrEmailField.sendKeys(username);
        return new MyAccountPage(webDriver);
    }

    public void enterUserPassword(String userPassword) {
        password.clear();
        password.sendKeys(userPassword);
    }

    public void clickSubmitButton() {
        Actions actions = new Actions(webDriver);
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        actions.moveToElement(loginSubmitButton).click().build().perform();
    }
//End of login form for registered user
    public String getErrorText() {
        wait.until(ExpectedConditions.visibilityOf(error));
        return error.getText();
    }

    public Boolean isShipToDifferentAddressCheckboxChecked() {
        return shipToDifferentAddressCheckbox.isSelected();
    }

    public List<String> getErrorLabels() {
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < errors.size(); i++) {
            labels.add(errors.get(i).getText());
        }
        return labels;
    }

    public String getFormValidationErrorText() {
        wait.until(ExpectedConditions.visibilityOf(formValidationError));
        return formValidationError.getText();
    }
//Lost password reminder;
    public MyAccountPage clickLostPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(lostPasswordButton));
        lostPasswordButton.click();
        return new MyAccountPage(webDriver);
    }







}
