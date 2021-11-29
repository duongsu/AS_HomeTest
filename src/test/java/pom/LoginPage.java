package pom;

import config.FrameworkConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends CommonPage{

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.manage().timeouts().pageLoadTimeout(Long.parseLong(FrameworkConfig.getInstance()
                .getProperty("test.configuration.loadingTimeout")), TimeUnit.SECONDS);
    }

    private static final By TXT_EMAIL_BY = By.cssSelector("input[placeholder='Email']");
    private static final By TXT_PASSWORD_BY = By.cssSelector("input[placeholder='Password']");
    private static final By BTN_LOG_IN_BY = By.cssSelector("button[type='submit']");

    //----------------------------------------------------------------------------

    public void inputUserNameAndPassword(String username, String password) {
        WebDriverWait wait = new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TXT_EMAIL_BY)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TXT_PASSWORD_BY)).sendKeys(password);
    }

    public void clickLogInButton() {
        new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(BTN_LOG_IN_BY)).click();
    }
}
