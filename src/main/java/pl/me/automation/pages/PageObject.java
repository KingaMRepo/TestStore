package pl.me.automation.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObject {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected static ThreadLocal<ChromeDriver> driver;

    public PageObject(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 25, 1000);
    }

}
