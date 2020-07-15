package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactPage extends Menu {

    @FindBy(tagName = "h1")
    private WebElement header;
    public ContactPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header,"Kontakt"));

    }


}
