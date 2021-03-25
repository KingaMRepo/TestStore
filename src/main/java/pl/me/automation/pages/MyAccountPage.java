package pl.me.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MyAccountPage extends Menu {

    @FindBy(tagName = "h1")
    private WebElement header;
    @FindBy(id = "reg_username")
    private WebElement userNameInput;
    @FindBy(id = "reg_email")
    private WebElement userEmailInput;
    @FindBy(id = "reg_password")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[@name= 'register']")
    private WebElement registerButton;
    @FindBy(id = "username")
    private WebElement userNameOrEmailInput;
    @FindBy(id = "password")
    private WebElement loginPasswordInput;
    @FindBy(id = "rememberme")
    private WebElement rememberMeCheckbox;
    @FindBy(xpath = ".//button[@name= 'login']")
    private WebElement submitButton;
    @FindBy(xpath = ".//p[contains(@class, 'LostPassword' )]")
    private WebElement passwordReminder;
    @FindBy(xpath = ".//div[@class = 'woocommerce-notices-wrapper']/ul/li")
    private WebElement errorAlert;
    @FindBy(xpath = ".//div[@class= 'woocommerce-MyAccount-content']")
    private WebElement loginEmail;
    @FindBy(xpath = ".//a[contains(text(), 'adresami płatności i dostawy')]")
    private WebElement addressAndBillingButton;
    @FindBy(xpath = ".//a[contains(@href, 'rozliczeniowy')]")
    private WebElement addressAndBillingEditButton;
    @FindBy(xpath = ".//a[contains(@href, 'przesylki')]")
    private WebElement shippingAddressEditButton;
    @FindBy(css = ".woocommerce-MyAccount-content>p>a")
    private WebElement logoutFromMyAccountButton;
    @FindBy(css = "div[class='woocommerce-notices-wrapper']+p>strong")
    private WebElement myAccountUserText;
    @FindBy(id = "identifierId")
    private WebElement gmailLogInUserName;
    @FindBy(css = "p>strong:first-of-type")
    private WebElement userNameText;
    @FindBy(css = "p[class~='woocommerce-LostPassword']>a")
    private WebElement lostPasswordReminder;
    @FindBy(css = ".woocommerce-MyAccount-content>p+p>a")
    private WebElement orderListButton;
    @FindBy(css = "[class~='woocommerce-orders-table__cell']>a")
    private WebElement orderListLastOrder;
    @FindBy(css = "[class~='woocommerce-MyAccount-content']>p+p>a+a")
    private WebElement paymentAndDeliveryAddressesButton;
    @FindBy(css = "[class='woocommerce-column__title']+address")
    private WebElement paymentAddress;
    @FindBy(css = "[class='woocommerce-orders-table__cell woocommerce-orders-table__cell-order-actions']>a")
    private List<WebElement> showOrderDetailsButtons;
    @FindBy(css = ".woocommerce-notices-wrapper+p")
    private WebElement paymentAndDeliveryAddressesMessage;
    @FindBy(css = ".woocommerce-MyAccount-content>div+p+p>a:last-child")
    private WebElement changePasswordAndAccountDetailsButton;
    @FindBy(css = ".woocommerce-message+p")
    private WebElement resetPasswordSuccessSendAlert;
    @FindBy(css = ".woocommerce-MyAccount-content>p+p")
    private WebElement welcomeToMyAccountText;
    @FindBy(css = ".woocommerce-notices-wrapper+p>a")
    private WebElement logOutButton;
    @FindBy(css = ".woocommerce-MyAccount-content>div>ul")
    private WebElement changePasswordAndAccountDetailsErrors;
    @FindBy(css = ".woocommerce-notices-wrapper+p")
    private WebElement welcomeAdminText;
    @FindBy(css = ".woocommerce-notices-wrapper +[class]>p")
    private WebElement lostPasswordInfoText;
    @FindBy(id = "user_login")
    private WebElement userLoginInput;
    @FindBy(css = "[name ='wc_reset_password']+button")
    private WebElement resetPasswordButton;
    @FindBy(css = ".woocommerce-message+p")
    private WebElement resetPasswordMessage;
    @FindBy(css = ".woocommerce-MyAccount-content>p")
    private WebElement loggedInUserPageText;
    @FindBy(css = "#account_display_name+span>em")
    private WebElement accountNameMessage;
    @FindBy(id = "account_first_name")
    private WebElement accountFirstName;
    @FindBy(id = "account_last_name")
    private WebElement accountLastName;
    @FindBy(id = "account_display_name")
    private WebElement accountDisplayName;
    @FindBy(id = "account_email")
    private WebElement accountEmail;
    @FindBy(id = "password_current")
    private WebElement currentPassword;
    @FindBy(id = "password_1")
    private WebElement newPassword;
    @FindBy(id = "password_2")
    private WebElement acceptNewPassword;
    @FindBy(css = "[class~='woocommerce-Button']")
    private WebElement changePasswordSubmitButton;
    @FindBy(name = "login")
    private WebElement loginButton;
    @FindBy(css = "div.woocommerce-message")
    private WebElement alert;
    @FindBy(css = "div.elementor-widget-container>div>p")
    private WebElement myAccountWelcomeAlert;
    @FindBy(css = ".woocommerce-error>li>strong")
    private List<WebElement> errors;
    @FindBy(css = "div.woocommerce-message")
    private WebElement accountDetailsChangedAlert;



    public MyAccountPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Moje konto"));
    }


    public MyAccountPage fillInMyAccountRegistration(String name, String email, String password) {
        userNameInput.clear();
        userNameInput.sendKeys(name);
        userEmailInput.clear();
        userEmailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        registerButton.click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage clickRegistrationButton() {
        registerButton.click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage fillInLoginAsUserForm(String nameOrEmail, String loginPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(userNameOrEmailInput));
        userNameOrEmailInput.clear();
        userNameOrEmailInput.sendKeys(nameOrEmail);
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(loginPassword);
        loginButton.click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage clickLoginButton() {
        changePasswordSubmitButton.click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage fillLoginFormWithRememberMeCheckbox(String nameOrEmail, String loginPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(userNameOrEmailInput));
        userNameOrEmailInput.clear();
        userNameOrEmailInput.sendKeys(nameOrEmail);
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(loginPassword);
        rememberMeCheckbox.click();
        loginButton.click();
        Actions actions = new Actions(webDriver);
        wait.until(ExpectedConditions.visibilityOf(changePasswordSubmitButton));
        actions.moveToElement(changePasswordSubmitButton).click().build().perform();
        return new MyAccountPage(webDriver);
    }

    public String getRegistrationAndLoginErrorText() {
        wait.until(ExpectedConditions.visibilityOf(errorAlert));
        return errorAlert.getText();
    }

    public Boolean isRegistrationAndLoginErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorAlert));
        return errorAlert.isDisplayed();
    }

    public String getDisplayedEmail() {
        return loginEmail.getText();
    }

    public MyAccountPage clickAddressAndBillingButton() {
        addressAndBillingButton.click();
        return new MyAccountPage(webDriver);
    }

    public String getMyAccountUserText() {
        return myAccountUserText.getText();
    }

    public String getUserNameText() {
        wait.until(ExpectedConditions.visibilityOf(welcomeAdminText));
        return userNameText.getText();

    }

    public Boolean isUserNameTextDisplayed() {
        return userNameText.isDisplayed();
    }

    public MyAccountPage fillInLostPasswordRecoveryForm(String userLoginOrEmail) {
        lostPasswordReminder.click();
        wait.until(ExpectedConditions.visibilityOf(userLoginInput));
        userLoginInput.clear();
        userLoginInput.sendKeys(userLoginOrEmail);
        wait.until(ExpectedConditions.visibilityOf(resetPasswordButton));
        resetPasswordButton.click();
        return new MyAccountPage(webDriver);
    }


    public void clickLastOrdersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderListButton));
        orderListButton.click();
    }

    public String getOrderListLastOrder() {
        return orderListLastOrder.getText().replace("#", "");
    }

    public MyAccountPage clickPaymentAndDeliveryAddressesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(paymentAndDeliveryAddressesButton));
        paymentAndDeliveryAddressesButton.click();
        return new MyAccountPage(webDriver);
    }

    public String getPaymentAddress() {
        wait.until(ExpectedConditions.visibilityOf(paymentAndDeliveryAddressesMessage));
        return paymentAddress.getText();
    }

    public void clickOrderDetailsButton(Integer index) {
        showOrderDetailsButtons.get(index).click();
    }

    public EditBillingAddressPage clickEditAddressAndBillingEditButton() {
        addressAndBillingEditButton.click();
        return new EditBillingAddressPage(webDriver);
    }

    public EditShippingAddressPage clickEditShippingAddressEditButtonButton() {
        shippingAddressEditButton.click();
        return new EditShippingAddressPage(webDriver);
    }

    public String getResetPasswordSuccessSendAlert() {
        return resetPasswordSuccessSendAlert.getText();
    }

    public Boolean isWelcomeToMyAccountTextDisplay() {
        return welcomeToMyAccountText.isDisplayed();
    }

    public MyAccountPage logOutFromMyAccountPage() {
        logOutButton.click();
        return new MyAccountPage(webDriver);
    }

    public Boolean isChangePasswordAndAccountDetailsErrorsDisplay() {
        return changePasswordAndAccountDetailsErrors.isDisplayed();

    }

    public String getResetPasswordText() {
        return resetPasswordMessage.getText();
    }

    public Boolean isLogeInUserPageDisplayed() {
        return loggedInUserPageText.isDisplayed();
    }


    public MyAccountPage fillInChangePasswordForm(String name, String lastName, String displayName, String email, String password, String newPasswordName, String acceptPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordAndAccountDetailsButton));
        changePasswordAndAccountDetailsButton.click();
        accountFirstName.clear();
        accountFirstName.sendKeys(name);
        accountLastName.clear();
        accountLastName.sendKeys(lastName);
        accountDisplayName.clear();
        accountDisplayName.sendKeys(displayName);
        accountEmail.clear();
        accountEmail.sendKeys(email);
        currentPassword.clear();
        currentPassword.sendKeys(password);
        newPassword.clear();
        newPassword.sendKeys(newPasswordName);
        acceptNewPassword.clear();
        acceptNewPassword.sendKeys(acceptPassword);
        changePasswordSubmitButton.click();
        return new MyAccountPage(webDriver);
    }

    public void clearChangePasswordFormFields() {
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordAndAccountDetailsButton));
        changePasswordAndAccountDetailsButton.click();
        accountFirstName.clear();
        accountLastName.clear();
        accountDisplayName.clear();
        accountEmail.clear();
        changePasswordSubmitButton.click();
    }

    public String getAlertText() {
        return alert.getText();
    }

    public Boolean isAccountDetailsChangedAlertAlertDisplayed() {
        return accountDetailsChangedAlert.isDisplayed();
    }

    public Boolean isMyAccountWelcomeTextDisplayed() {
        return myAccountWelcomeAlert.isDisplayed();
    }

    public List<String> getErrorLabels() {
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < errors.size(); i++) {
            labels.add(errors.get(i).getText());
        }
        return labels;
    }




}