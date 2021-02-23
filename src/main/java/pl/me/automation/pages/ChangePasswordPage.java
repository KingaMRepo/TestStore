package pl.me.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ChangePasswordPage extends Menu {

    @FindBy(css="#account_display_name+span>em")
    private WebElement accountNameMessage;
    @FindBy(id="account_first_name")
    private WebElement accountFirstName;
    @FindBy(id="account_last_name")
    private WebElement accountLastName;
    @FindBy(id="account_display_name")
    private WebElement accountDisplayName;
    @FindBy(id="account_email")
    private WebElement accountEmail;
    @FindBy(id="password_current")
    private WebElement currentPassword;
    @FindBy(id="password_1")
    private WebElement newPassword;
    @FindBy(id="password_2")
    private WebElement acceptNewPassword;
    @FindBy(css ="[class~='woocommerce-Button']")
    private WebElement submitButton;
    @FindBy(css="div.woocommerce-message")
    private WebElement alert;
    @FindBy(css="div.elementor-widget-container>div>p")
    private WebElement myAccountWelcomeAlert;
    @FindBy(css=".woocommerce-error>li>strong")
    private List<WebElement> errors;
    @FindBy(css="div.woocommerce-message")
    private WebElement accountDetailsChangedAlert;




    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(accountNameMessage, "W taki sposób twoja nazwa zostanie wyświetlona w sekcji Moje konto i w twoich opiniach"));
    }


    public void fillInChangePasswordForm(String name, String lastName, String displayName, String email, String password, String newPasswordName, String acceptPassword) {
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

        }

    public void clearChangePasswordFormFields() {
        accountFirstName.clear();
        accountLastName.clear();
        accountDisplayName.clear();
        accountEmail.clear();
    }

    public MyAccountPage submitPassword(){
        submitButton.click();
        return new MyAccountPage(webDriver);
    }


    public String getAlertText(){
        return alert.getText();
    }

    public Boolean isAccountDetailsChangedAlertAlertDisplayed(){
        return accountDetailsChangedAlert.isDisplayed();
    }

    public Boolean isMyAccountWelcomeTextDisplayed(){
        return myAccountWelcomeAlert.isDisplayed();
    }

    public List<String> getErrorLabels(){
        List<String>labels = new ArrayList<>();
        for (int i = 0; i <errors.size(); i++) {
            labels.add(errors.get(i).getText());
        }
        return labels;
    }



}
