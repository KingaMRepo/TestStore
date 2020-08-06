package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LostPasswordReminderPage extends Menu {

    public static final String resetText = "E-mail z linkiem do zresetowania hasła został wysłany na adres przypisany do twojego konta, może minąć kilka minut zanim pojawi się on w twojej skrzynce. Proszę poczekaj co najmniej 10 minut przed ponowną próbą resetu hasła.";

    @FindBy(css=".woocommerce-notices-wrapper +[class]>p")
    private WebElement lostPasswordInfoText;
    @FindBy(id="user_login")
    private WebElement userLoginInput;
    @FindBy(css="[name ='wc_reset_password']+button")
    private WebElement resetPasswordButton;
    @FindBy(css=".woocommerce-message+p")
    private WebElement resetPasswordMessage;



    public LostPasswordReminderPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(lostPasswordInfoText, "Zapomniałeś hasła? Wpisz swoją nazwę użytkownika lub adres e-mail. Dostaniesz link do stworzenia nowego hasła mailem."));
    }

    public void insertUserLoginOrEmail(String userLoginOrEmail){
        wait.until(ExpectedConditions.visibilityOf(userLoginInput));
        userLoginInput.clear();
        userLoginInput.sendKeys(userLoginOrEmail);
    }


    public void clickResetPasswordButton(){
        wait.until(ExpectedConditions.visibilityOf(resetPasswordButton));
        resetPasswordButton.click();
    }

    public String getResetPasswordText() {
        return resetPasswordMessage.getText();
    }

}
