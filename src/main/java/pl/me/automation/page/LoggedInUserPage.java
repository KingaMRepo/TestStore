package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoggedInUserPage extends Menu {

    @FindBy(css=".woocommerce-MyAccount-content>p")
    private WebElement loggedInUserPageText;

    public LoggedInUserPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(loggedInUserPageText, "Witaj "));
    }

    public Boolean isLogeInUserPageDisplayed(){
        return loggedInUserPageText.isDisplayed();
    }



}
