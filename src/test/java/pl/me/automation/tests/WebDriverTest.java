package pl.me.automation.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pl.me.automation.config.WebDriverType;
import pl.me.automation.pages.HomePage;

import java.util.stream.Stream;

public class WebDriverTest {

    @ParameterizedTest
    @MethodSource("provideWebDriverType")
    public void shouldOpenStoreTestProject(WebDriverType webDriverType){
        WebDriver webDriver = webDriverType.create();
        webDriver.get("https://www.storetestproject.hekko24.pl/");
        new HomePage(webDriver);
        webDriver.quit();
    }

    private static Stream<Arguments> provideWebDriverType(){
        return Stream.of(
        Arguments.of(WebDriverType.CHROME)

        );
    }

}

