package pl.me.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends Menu {

    @FindBy(tagName = "h1")
    private WebElement header;
    @FindBy(id = "wpforms-32-field_7")
    private WebElement userNameInput;
    @FindBy(id ="wpforms-32-field_7-last")
    private WebElement userLastName;
    @FindBy(id="wpforms-32-field_6")
    private WebElement messageSubjectSelect;
    @FindBy(id="wpforms-32-field_4")
    private WebElement userEmailInput;
    @FindBy(id="wpforms-32-field_2")
    private WebElement userMessageInput;
    @FindBy(id="wpforms-submit-32")
    private WebElement sendMessageButton;
    @FindBy(id="wpforms-confirmation-32")
    private WebElement contactMessage;
    @FindBy(id="wpforms-32-field_4-error")
    private WebElement emailValidationError;
    @FindBy(id="wpforms-32-field_7-error")
    private WebElement nameValidationError;
    @FindBy(id="wpforms-32-field_2-error")
    private WebElement messageValidationError;


    public ContactPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.textToBePresentInElement(header,"Kontakt"));

    }
    public ContactPage fillInContactForm(String name, String lastName, String value, String email, String message) {
        userNameInput.clear();
        userNameInput.sendKeys(name);
        userLastName.clear();
        userLastName.sendKeys(lastName);
        Select select = new Select(messageSubjectSelect);
        select.selectByValue(value);
        userEmailInput.clear();
        userEmailInput.sendKeys(email);
        userMessageInput.sendKeys(message);
        sendMessageButton.click();
        return new ContactPage(webDriver);
    }

    public void clickSendMessageButton(){
        wait.until(ExpectedConditions.elementToBeClickable(sendMessageButton));
        sendMessageButton.click();
    }

    public Boolean isContactMessageDisplayed(){
        return contactMessage.isDisplayed();
    }

    public Boolean isEmailValidationErrorDisplayed(){
        return emailValidationError.isDisplayed();
    }
    public Boolean isNameValidationError(){
        return nameValidationError.isDisplayed();
    }

    public Boolean isMessageValidationError(){
        return messageValidationError.isDisplayed();
    }
}
