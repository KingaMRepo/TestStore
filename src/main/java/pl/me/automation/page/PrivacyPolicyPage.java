package pl.me.automation.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrivacyPolicyPage extends Menu {
    @FindBy(css=".elementor-widget-container>div>ul>:nth-child(5)")
    private WebElement policyText;
    @FindBy(className = ".elementor-widget-container>div>ul>:last-child")
    private WebElement privacyPolicyText;

    public PrivacyPolicyPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(policyText, "Komu udostępniamy dane" ));
    }

}
