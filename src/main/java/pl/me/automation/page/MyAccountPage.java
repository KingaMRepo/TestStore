package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    @FindBy(css= ".woocommerce-MyAccount-content>p+p>a")
    private WebElement orderListButton;
    @FindBy(css= "[class~='woocommerce-orders-table__cell']>a" )
    private WebElement orderListLastOrder;
    @FindBy(css= "[class~='woocommerce-MyAccount-content']>p+p>a+a")
    private WebElement paymentAndDeliveryAddressesButton;
    @FindBy(css= "[class='woocommerce-column__title']+address")
    private WebElement paymentAddress;
    @FindBy(css="[class='woocommerce-orders-table__cell woocommerce-orders-table__cell-order-actions']>a")
    private List<WebElement> showOrderDetailsButtons;
    @FindBy(css= ".woocommerce-notices-wrapper+p")
    private WebElement paymentAndDeliveryAddressesMessage;
    @FindBy(css= ".woocommerce-MyAccount-content>div+p+p>a:last-child")
    private WebElement changePasswordAndAccountDetailsButton;
    @FindBy(css= "[name='wc_reset_password']+button")
    private WebElement resetPasswordSubmitButton;
    @FindBy(id = "user_login")
    private WebElement lostPasswordUserLogin;
    @FindBy(css= ".woocommerce-message+p")
    private WebElement resetPasswordSuccessSendAlert;
    @FindBy(css= ".woocommerce-MyAccount-content>p+p")
    private WebElement welcomeToMyAccountText;
    @FindBy(css = ".woocommerce-notices-wrapper+p>a")
    private WebElement logOutButton;
    @FindBy(css = ".woocommerce-MyAccount-content>div>ul")
    private WebElement changePasswordAndAccountDetailsErrors;
    @FindBy(css = ".woocommerce-notices-wrapper +p")
    private WebElement welcomeAdminText;





    public MyAccountPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Moje konto"));
    }

    public void enterRegisterUserName(String name) {
        userNameInput.clear();
        userNameInput.sendKeys(name);
    }

    public void enterRegisterUserEmail(String email) {
        userEmailInput.clear();
        userEmailInput.sendKeys(email);
    }

    public void enterRegisterUserPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public LoggedInUserPage clickRegister() {
        registerButton.click();
        return new LoggedInUserPage(webDriver);
    }

    public MyAccountPage clickRegisterFalse() {
        registerButton.click();
        return new MyAccountPage(webDriver);
    }

    public void enterLoginUserNameOrEmail(String nameOrEmail) {
        wait.until(ExpectedConditions.elementToBeClickable(userNameOrEmailInput));
        userNameOrEmailInput.clear();
        userNameOrEmailInput.sendKeys(nameOrEmail);
    }

    public void enterLoginUserLoginPassword(String loginPassword) {
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(loginPassword);
    }

    public void clickLoginRememberMeCheckbox() {
        rememberMeCheckbox.click();

    }

    public MyAccountPage clickLoginSubmit() {
        Actions actions = new Actions(webDriver);
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        actions.moveToElement(submitButton).click().build().perform();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage clickLoginPasswordReminder() {
        passwordReminder.click();
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

    public LostPasswordReminderPage clickLostPasswordReminderButton(){
        lostPasswordReminder.click();
        return new LostPasswordReminderPage(webDriver);
    }

    public void clickLastOrdersButton(){
        wait.until(ExpectedConditions.elementToBeClickable(orderListButton));
        orderListButton.click();
    }

    public String getOrderListLastOrder(){
        return orderListLastOrder.getText().replace("#", "");
    }

    public MyAccountPage clickPaymentAndDeliveryAddressesButton(){
        wait.until(ExpectedConditions.elementToBeClickable(paymentAndDeliveryAddressesButton));
        paymentAndDeliveryAddressesButton.click();
        return new MyAccountPage(webDriver);
    }

    public String getPaymentAddress(){
        wait.until(ExpectedConditions.visibilityOf(paymentAndDeliveryAddressesMessage));
        return paymentAddress.getText();
    }

    public void clickOrderDetailsButton(Integer index)
    {
        showOrderDetailsButtons.get(index).click();
    }


    public ChangePasswordPage clickIntoChangePasswordAndAccountDetailsButton(){
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordAndAccountDetailsButton));
        changePasswordAndAccountDetailsButton.click();
        return new ChangePasswordPage(webDriver);
    }

    public EditBillingAddressPage clickEditAddressAndBillingEditButton(){
        addressAndBillingEditButton.click();
        return new EditBillingAddressPage(webDriver);
    }

    public EditShippingAddressPage clickEditShippingAddressEditButtonButton(){
        shippingAddressEditButton.click();
        return new EditShippingAddressPage(webDriver);
    }


    public void enterLoginOrEmailToLostPasswordUserField(String user) {
        lostPasswordUserLogin.clear();
        lostPasswordUserLogin.sendKeys(user);
    }

    public MyAccountPage clickResetPasswordSubmitButton() {
        resetPasswordSubmitButton.click();
        return new MyAccountPage(webDriver);
    }

    public String getResetPasswordSuccessSendAlert(){
        return resetPasswordSuccessSendAlert.getText();
    }

   public Boolean isWelcomeToMyAccountTextDisplay(){
       return welcomeToMyAccountText.isDisplayed();
   }

   public MyAccountPage logOutFromMyAccountPage(){
       logOutButton.click();
       return new MyAccountPage(webDriver);
   }

    public Boolean isChangePasswordAndAccountDetailsErrorsDisplay(){
        return changePasswordAndAccountDetailsErrors.isDisplayed();

    }


}