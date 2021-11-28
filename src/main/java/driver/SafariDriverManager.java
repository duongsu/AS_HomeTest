package driver;

import config.FrameworkConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;

import java.io.File;

class SafariDriverManager extends AbstractDriverManager {

    private static SafariDriverService safariDriverService;

    @Override
    void startService() {
        if (null == safariDriverService) {
            try {
                safariDriverService = new SafariDriverService.Builder()
                        .usingDriverExecutable(new File(
                                FrameworkConfig.getInstance().getProperty("selenium.webdriver.safaribrowser.path")))
                        .usingAnyFreePort()
                        .build();
                safariDriverService.start();
            } catch (Exception e) {
                throw new RuntimeException("Cannot start selenium service!!" + e);
            }
        }
    }

    @Override
    void stopService() {
        if (null != safariDriverService && safariDriverService.isRunning())
            safariDriverService.stop();
    }

    @Override
    void createDriver() {
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }

    @Override
    void createRemoteDriver() {
    }
}
