package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AboutPage extends Menu {

    @FindBy(tagName = "h1")
    private WebElement header;
    public AboutPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header, "O nas"));
    }
}
