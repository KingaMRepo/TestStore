package pl.me.automation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.pages.HomePage;
import pl.me.automation.utils.TestDataReader;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest extends TestDataReader  {

    protected WebDriver webDriver;
    protected HomePage homePage;
    protected TestDataReader testDataReader;
    protected ExtentHtmlReporter reporter;
    protected ExtentReports reports;


    @BeforeEach
    public void init() {
        webDriver = WebDriverType.CHROME.create();
        webDriver.get("https://www.storetestproject.hekko24.pl/");
        homePage = new HomePage(webDriver);
        homePage.clickCookie();

    }

    @BeforeAll
    public void tearUpReporter(){
        testDataReader = new TestDataReader();
        reporter = new ExtentHtmlReporter("src//main//resources//reports//index.html");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
    }

//    @AfterEach
//    public void tearDown() {
//        webDriver.quit();
//    }

    @AfterAll
    public void tearDownReporter(){
        reporter.flush();
        reports.flush();

    }


}