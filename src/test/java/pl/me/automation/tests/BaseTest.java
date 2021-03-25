package pl.me.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.pages.HomePage;
import pl.me.automation.utils.TestDataReader;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest extends TestDataReader {

    protected WebDriver webDriver;
    protected HomePage homePage;
    protected TestDataReader testDataReader;


    @BeforeEach
    public void init() {
        webDriver = WebDriverType.CHROME.create();
        webDriver.get("https://www.storetestproject.hekko24.pl/");
        homePage = new HomePage(webDriver);
        homePage.clickCookie();
    }

    @BeforeAll
    public void tearUp() {
        testDataReader = new TestDataReader();
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }


}


