package pom;

import config.FrameworkConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ManufacturingPage extends CommonPage {

    private WebDriver webDriver;

    public ManufacturingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.manage().timeouts().pageLoadTimeout(Long.parseLong(FrameworkConfig.getInstance()
                .getProperty("test.configuration.loadingTimeout")), TimeUnit.SECONDS);
    }

    private static final By TXT_PRODUCT_NAME_BY = By.xpath("//div[contains(@name, 'product_id')]//input");
    private static final String LBL_PRODUCT_NAME_STRING = "//a[text()='%s']";
    private static final By LK_ADD_LINE_BY = By.xpath("//a[text()='Add a line']");
    private static final By TXT_PRODUCT_NAME_IN_LIST_BY = By.xpath("//div[contains(@class, 'list_view')]//div[contains(@name, 'product_id')]//input");
    private static final By TXT_TO_CONSUME_BY = By.cssSelector("input[name='product_uom_qty']");
    private static final By BTN_CONFIRM_BY = By.cssSelector("button[name='action_confirm']");
    private static final By BTN_APPLY_BY = By.cssSelector("button[name='process']");
    private static final By BTN_DONE_BY = By.xpath("//button/span[text()='Mark as Done']");
    private static final By BTN_SCRAP_BY = By.cssSelector("button[name='button_scrap']");
    private static final By BTN_UNBUILD_BY = By.cssSelector("button[name='button_unbuild']");
    private static final By BTN_CURRENT_STATE_BY = By.cssSelector("button[title='Current state']");
    private static final By LBL_PRODUCT_NAME_BY = By.xpath("//a[contains(@name, 'product_id')]/span");
    private static final By LBL_PRODUCT_QUANTITY_BY = By.cssSelector("span[name='qty_producing']");
    private static final String LBL_PRODUCT_NAME_IN_LIST_STRING = "td[title='%s']";
    private static final By LBL_TO_CONSUME_BY = By.cssSelector("span[name='product_uom_qty']");

    //----------------------------------------------------------------------------

    public void inputAndSelectProductName(String productName) {
        WebDriverWait wait = new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TXT_PRODUCT_NAME_BY)).sendKeys(productName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LBL_PRODUCT_NAME_STRING, productName)))).click();
    }

    public void clickAddALineLink() {
        new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(LK_ADD_LINE_BY)).click();
    }

    public void inputAndSelectProductNameInListView(String productName) {
        new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(TXT_PRODUCT_NAME_IN_LIST_BY)).sendKeys(productName);
        List<WebElement> list = webDriver.findElements(By.xpath(String.format(LBL_PRODUCT_NAME_STRING, productName)));
        while (list.size() < 2) {
            list = webDriver.findElements(By.xpath(String.format(LBL_PRODUCT_NAME_STRING, productName)));
        }
        list.get(1).click();
    }

    public void inputToConsumeInListView(String toConsume) {
        WebDriverWait wait = new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TXT_TO_CONSUME_BY)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(TXT_TO_CONSUME_BY)).sendKeys(String.valueOf(toConsume));
    }

    public void clickConfirmButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(BTN_CONFIRM_BY));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BTN_CONFIRM_BY)).click();
    }

    public void clickApplyButton() {
        new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(BTN_APPLY_BY)).click();
    }


    public void clickMarkAsDoneButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(BTN_DONE_BY));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BTN_DONE_BY)).click();
    }

    public String getCurrentState() {
        WebDriverWait wait = new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(BTN_SCRAP_BY)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOfElementLocated(BTN_UNBUILD_BY)).isDisplayed();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(BTN_CURRENT_STATE_BY)).getText();
    }

    public String getProductName() {
        return new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(LBL_PRODUCT_NAME_BY)).getText();
    }

    public String getProductQuantity() {
        return new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(LBL_PRODUCT_QUANTITY_BY)).getText();
    }

    public String getProductNameInList(String productName) {
        return new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(String.format(LBL_PRODUCT_NAME_IN_LIST_STRING, productName)))).getText();
    }

    public String getToConsume() {
        return new WebDriverWait(webDriver, RENDER_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(LBL_TO_CONSUME_BY)).getText();
    }
}
