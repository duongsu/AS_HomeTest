package pom;

import config.FrameworkConfig;
import driver.DriverManagerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage {

    public static final int RENDER_ELEMENT_TIMEOUT = Integer.parseInt(FrameworkConfig.getInstance().getProperty("test.configuration.renderElementsTimeout"));

    public static final By BTN_CREATE_BY = By.cssSelector("button[accesskey='c']");
    public static final By BTN_SAVE_BY = By.cssSelector("button[accesskey='s']");
    public static final By ICON_APPLICATION_BY = By.cssSelector("a[title='Applications']");

    //----------------------------------------------------------------------------

    public void clickCreateButton() {
        new WebDriverWait(DriverManagerFactory.getExistingManager().getDriver(), RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(BTN_CREATE_BY)).click();
    }

    public void clickSaveButton() {
        new WebDriverWait(DriverManagerFactory.getExistingManager().getDriver(), RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(BTN_SAVE_BY)).click();
    }

    public void clickApplicationIcon() {
        new WebDriverWait(DriverManagerFactory.getExistingManager().getDriver(), RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(ICON_APPLICATION_BY)).click();
    }
}
