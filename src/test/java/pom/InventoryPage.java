package pom;

import config.FrameworkConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InventoryPage extends CommonPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(FrameworkConfig.getInstance()
                .getProperty("test.configuration.loadingTimeout")), TimeUnit.SECONDS);
    }

    private static final By MENU_PRODUCTS_BY = By.xpath("//a[contains(text(),'Products')]");
    private static final By SUB_MENU_PRODUCTS_BY = By.xpath("//a/span[(text()='Products')]");

    //----------------------------------------------------------------------------

    public void clickProductsMenu() {
        new WebDriverWait(driver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(MENU_PRODUCTS_BY)).click();
    }

    public void clickProductsSubMenu() {
        new WebDriverWait(driver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(SUB_MENU_PRODUCTS_BY)).click();
    }
}
