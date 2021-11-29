package pom;

import config.FrameworkConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends CommonPage{

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(FrameworkConfig.getInstance()
                .getProperty("test.configuration.loadingTimeout")), TimeUnit.SECONDS);
    }

    private static final By LK_INVENTORY_BY = By.xpath("//div[text()='Inventory']");
    private static final By LK_MANUFACTURING_BY = By.xpath("//div[text()='Manufacturing']");

    //----------------------------------------------------------------------------

    public void clickInventoryLink() {
        new WebDriverWait(driver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(LK_INVENTORY_BY)).click();
    }

    public void clickManufacturingLink() {
        new WebDriverWait(driver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(LK_MANUFACTURING_BY)).click();
    }
}
