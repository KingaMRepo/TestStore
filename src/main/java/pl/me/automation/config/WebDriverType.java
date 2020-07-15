package pl.me.automation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public enum WebDriverType {
    CHROME{
        @Override
        public WebDriver create() {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            return new ChromeDriver(chromeOptions);
        }
    }, FIREFOX{
        @Override
        public WebDriver create() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }, OPERA{
        @Override
        public WebDriver create() {
            WebDriverManager.operadriver().setup();
            return new OperaDriver();
        }
    }, IE{
        @Override
        public WebDriver create() {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver();
        }
    }, EDGE{
        @Override
        public WebDriver create() {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }
    };

    public abstract WebDriver create();




}
