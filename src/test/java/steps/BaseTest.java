package steps;

import config.FrameworkConfig;
import driver.AbstractDriverManager;
import driver.DriverManagerFactory;
import driver.DriverType;
import environment.WebPhases;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(value = WebPhases.class)
public abstract class BaseTest {

    private static AbstractDriverManager driverManager;
    public static WebDriver webDriver;

    @BeforeSuite
    public void setUp() {
        driverManager = DriverManagerFactory.getManager(DriverType.of(FrameworkConfig.getInstance()
                .getProperty("test.configuration.browser")));
        webDriver = driverManager.getDriver();
    }

    @AfterSuite
    public void tearDown() {
        DriverManagerFactory.getExistingManager().quitDriver();
    }
}
