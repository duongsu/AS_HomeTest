package driver;

import config.FrameworkConfig;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class ChromeDriverManager extends AbstractDriverManager {

    private ChromeDriverService chService;

    @Override
    void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(
                                FrameworkConfig.getInstance().getProperty("selenium.webdriver.chromebrowser.path")))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                throw new RuntimeException("Cannot start selenium service!!" + e);
            }
        }
    }

    @Override
    void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("start-maximised");
        options.setAcceptInsecureCerts(Boolean.parseBoolean(
                FrameworkConfig.getInstance().getProperty("selenium.acceptInsecureCerts")));
        options.setHeadless(Boolean.parseBoolean(FrameworkConfig.getInstance().getProperty("selenium.headless")));
        System.setProperty("webdriver.chrome.driver", FrameworkConfig.getInstance().getProperty("selenium.webdriver.chromebrowser.path"));
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }

    @Override
    void createRemoteDriver() {
    }

}
