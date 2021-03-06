package pl.me.automation.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    @FindBy(css = "input#billing_state")
    private WebElement paymentProvince;
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
    @FindBy(css = ".lost_password>a")
    private WebElement lostPasswordButton;
    @FindBy(className = "input-radio")
    private List<WebElement> paymentMethods;
    @FindBy(id = "payment_method_payu")
    private WebElement payUPayment;
    @FindBy(css = "input#wc-stripe-payment-token-new")
    private WebElement newUPaymentMethodRadioButton;
    @FindBy(css = ".InputContainer> input")
    private WebElement payURadioButton;
    @FindBy(css = "div.CardNumberField-input-wrapper>.InputContainer>input")
    private WebElement cardNumberField;
    @FindBy(css = "div#stripe-card-element>div>iframe")
    private WebElement stripePaymentBox;
    @FindBy(css = ".InputContainer>[placeholder='Numer karty']")
    private WebElement stripeCardNumberInput;
    @FindBy(id = "payment_method_payu")
    private WebElement payUPaymentMethod;
    @FindBy(css = "ul.woocommerce-error>li")
    private WebElement unableToProcessOrderAlert;
    @FindBy(css = "[class*='woocommerce-NoticeGroup']")
    private WebElement unableToProcessOrderAlertField;
    @FindBy(css = ".stripe-source-errors>ul>li")
    private WebElement stipeCardValidityError;
    @FindBy(css = ".stripe-source-errors>ul")
    private WebElement stipeErrorField;
    @FindBy(css = "ul.woocommerce-error")
    private WebElement billingError;
    @FindBy(css= ".woocommerce-error li:first-child")
    private WebElement billingNameError;
    @FindBy(id = "user_login")
    private WebElement lostPasswordUserLogin;
    @FindBy(css = "[name='wc_reset_password']+button")
    private WebElement resetPasswordSubmitButton;


    public PaymentPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(h3Header, "Dane płatności"));
    }


    public void fillInPaymentForm(String name, String lastName, Integer CountryIndex, String address, String city, String province, String postCode, String phone, String email, String userName, String password) {
            paymentUserNameInput.clear();
            paymentUserNameInput.sendKeys(name);
            paymentUserLastNameInput.clear();
            paymentUserLastNameInput.sendKeys(lastName);
            Select select = new Select(paymentCountrySelect);
            select.selectByIndex(CountryIndex);
            paymentAddress.clear();
            paymentAddress.sendKeys(address);
            paymentCity.clear();
            paymentCity.sendKeys(city);
            paymentProvince.clear();
            paymentProvince.sendKeys(province);
            paymentPostCode.clear();
            paymentPostCode.sendKeys(postCode);
            paymentPhone.clear();
            paymentPhone.sendKeys(phone);
            paymentEmail.clear();
            paymentEmail.sendKeys(email);
            paymentAccountUsername.clear();
            paymentAccountUsername.sendKeys(userName);
            paymentPassword.clear();
            paymentPassword.sendKeys(password);
        }

    public void deselectShipToDifferentAddressCheckbox() {
        shipToDifferentAddressCheckbox.click();
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

    public void applyCouponCode(String couponCode) {
        clickToAddCouponButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(applyCouponButton));
        couponCodeInput.clear();
        couponCodeInput.sendKeys(couponCode);
        applyCouponButton.click();
    }

    public Boolean isAppliedCouponAlertMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(appliedCouponAlertMessage));
        return appliedCouponAlertMessage.isDisplayed();
    }


    public void fillInShippingForm(String name, String lastName, Integer index, String address, String shippingRegion, String postCode, String city) {
        shippingFirstName.clear();
        shippingFirstName.sendKeys(name);
        shippingLastName.clear();
        shippingLastName.sendKeys(lastName);
        Select select = new Select(shippingCountrySelect);
        select.selectByIndex(index);
        shippingAddress.clear();
        shippingAddress.sendKeys(address);
        shippingState.clear();
        shippingState.sendKeys(shippingRegion);
        shippingPostcode.clear();
        shippingPostcode.sendKeys(postCode);
        shippingCity.clear();
        shippingCity.sendKeys(city);
    }

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

    public void logInOnPaymentPageAsRegisteredUser(String userName, String userPassword){
        accountLoginButton.click();
        wait.until(ExpectedConditions.visibilityOf(userNameOrEmailField));
        userNameOrEmailField.clear();
        userNameOrEmailField.sendKeys(userName);
        password.clear();
        password.sendKeys(userPassword);
        Actions actions = new Actions(webDriver);
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        actions.moveToElement(loginSubmitButton).click().build().perform();
    }

    public String getErrorText() {
        wait.until(ExpectedConditions.visibilityOf(error));
        return error.getText();
    }

    public Boolean isShipToDifferentAddressCheckboxChecked() {
        return shipToDifferentAddressCheckbox.isSelected();
    }

    public List<String> getErrorLabels() {
        wait.until(ExpectedConditions.visibilityOf(billingError));
        return errors.stream()
                .map(price -> price.getText())
                .collect(Collectors.toList());

    }

    public String getFormValidationErrorText() {
        wait.until(ExpectedConditions.visibilityOf(formValidationError));
        return formValidationError.getText();
    }

    public MyAccountPage fillInLostPasswordRecoveryForm(String user) {
        accountLoginButton.click();
        wait.until(ExpectedConditions.visibilityOf(userNameOrEmailField));
        lostPasswordButton.click();
        lostPasswordUserLogin.clear();
        lostPasswordUserLogin.sendKeys(user);
        resetPasswordSubmitButton.click();
        return new MyAccountPage(webDriver);
    }


    public PaymentPage choosePaymentMethod(String paymentMethod) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(payUPayment).build().perform();

        WebElement selectedPaymentMethod = null;
        for (int i = 0; i < paymentMethods.size(); i++) {
            if (paymentMethods.get(i).getAttribute("value").equals(paymentMethod)) {
                selectedPaymentMethod = paymentMethods.get(i);
            }
        }
        if (selectedPaymentMethod != null) {
            selectedPaymentMethod.click();
        }

        return new PaymentPage(webDriver);
    }

    public void clickNewUPaymentMethodRadioButton() {
        Actions actions = new Actions(webDriver);
        wait.until(ExpectedConditions.visibilityOf(newUPaymentMethodRadioButton));
        actions.moveToElement(newUPaymentMethodRadioButton).click().build().perform();

    }

    public void findElementInFrame(String cardNumber, String date, String CVCNumber) {
        webDriver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(stripePaymentBox));
        wait.until(ExpectedConditions.elementToBeClickable(stripeCardNumberInput));
        stripeCardNumberInput.sendKeys(cardNumber, date, CVCNumber);
        webDriver.switchTo().defaultContent();
    }

    public Boolean checkIfPayURadioButtonIsSelected() {
        return payUPaymentMethod.isSelected();
    }

    public String getUnableToProcessOrderAlertText() {
        wait.until(ExpectedConditions.visibilityOf(unableToProcessOrderAlertField));
        return unableToProcessOrderAlert.getText();
    }

    public String getStripeCardValidityErrorText() {
        wait.until(ExpectedConditions.visibilityOf(stipeErrorField));
        return stipeCardValidityError.getText();
    }

    public String getBillingNameErrorText() {
        wait.until(ExpectedConditions.visibilityOf(billingNameError));
        return billingNameError.getText();
    }


    public void acceptTermsAndConditionsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsCheckbox));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(termsAndConditionsCheckbox).build().perform();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        termsAndConditionsCheckbox.click();
    }

    public OrderPage paymentAccept() {
        paymentSubmitButton.click();
        return new OrderPage(webDriver);
    }




}