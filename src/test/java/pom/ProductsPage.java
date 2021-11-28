package pom;

import config.FrameworkConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductsPage extends CommonPage {

    private WebDriver webDriver;

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.manage().timeouts().pageLoadTimeout(Long.parseLong(FrameworkConfig.getInstance()
                .getProperty("test.configuration.loadingTimeout")), TimeUnit.SECONDS);
    }

    public static final By TXT_PRODUCT_NAME_BY = By.cssSelector("input[placeholder='Product Name']");

    //----------------------------------------------------------------------------

    public void inputProductName(String productName) {
        new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(TXT_PRODUCT_NAME_BY)).sendKeys(productName);
    }
}
