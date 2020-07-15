package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    private WebElement remembermeCheckbox;
    @FindBy(xpath = ".//button[@name= 'login']")
    private WebElement submitButton;
    @FindBy(xpath = ".//p[contains(@class, 'LostPassword' )]")
    private WebElement passwordReminder;
    @FindBy(xpath = ".//div[@class = 'woocommerce-notices-wrapper']")
    private WebElement errorAlert;
    @FindBy(xpath = ".//div[@class= 'woocommerce-MyAccount-content']")
    private WebElement loginEmail;
    @FindBy(xpath = ".//a[contains(text(), 'adresami płatności i dostawy')]")
    private WebElement addressAndBillingButton;
    @FindBy(xpath = ".//a[contains(@href, 'rozliczeniowy')]")
    private WebElement addressAndBillingEditButton;



    public MyAccountPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "Moje konto"));
    }

    public void enterUserName(String name) {
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

    public MyAccountPage clickRegister() {
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
        remembermeCheckbox.click();

    }

    public MyAccountPage clickLoginSubmit() {
        submitButton.click();
        return new MyAccountPage(webDriver);

    }
    public MyAccountPage clickLoginPasswordReminder() {
        passwordReminder.click();
        return new MyAccountPage(webDriver);
    }

    public Boolean isRegistrationError() {
        return errorAlert.isDisplayed();
    }

    public String getDisplayedEmail()
    {
        return loginEmail.getText();
    }

    public MyAccountPage clickAddressAndBillingButton(){
        addressAndBillingButton.click();
        return new MyAccountPage(webDriver);
    }

    public EditAddressPage clickAddressAndBillingEditButton(){
        addressAndBillingEditButton.click();
        return new EditAddressPage(webDriver, "Adres rozliczeniowy");
    }



}