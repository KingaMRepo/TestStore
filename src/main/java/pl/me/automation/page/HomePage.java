package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Menu{

    @FindBy(xpath = "//div[@data-id='523ed945']/div/div/a")
    private WebElement buyButton;
    @FindBy(id = "cn-accept-cookie")
    private WebElement acceptCookieButton;

    public HomePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(buyButton));
    }
    public void clickCookie(){
        acceptCookieButton.click();
    }
}
