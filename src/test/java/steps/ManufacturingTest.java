package steps;

import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.ManufacturingPage;

public class ManufacturingTest extends BaseTest {

    private ManufacturingPage manufacturingPage;
    private HomePage homePage;
    private InventoryTest inventoryTest = new InventoryTest();

    //------------------------------------------------------------------------------------------------------------------

    @Test(groups = {"P1"})
    public void createManufacturingOrderTest() {
        manufacturingPage = new ManufacturingPage(webDriver);
        homePage = new HomePage(webDriver);
        String productQuantity = "1.00";
        String toConsume = "1.00";

        inventoryTest.createProductTest();
        manufacturingPage.clickApplicationIcon();
        homePage.clickManufacturingLink();
        manufacturingPage.clickCreateButton();
        manufacturingPage.inputAndSelectProductName(inventoryTest.productName);
        manufacturingPage.clickAddALineLink();
        manufacturingPage.inputAndSelectProductNameInListView(inventoryTest.productName);
        manufacturingPage.inputToConsumeInListView(toConsume);
        manufacturingPage.clickSaveButton();
        manufacturingPage.clickConfirmButton();
        manufacturingPage.clickMarkAsDoneButton();
        manufacturingPage.clickApplyButton();

        Assert.assertEquals(manufacturingPage.getCurrentState(), "DONE", "Current state is not correct!");
        Assert.assertEquals(manufacturingPage.getProductName(), inventoryTest.productName, "Product Name is not correct!");
        Assert.assertEquals(manufacturingPage.getProductQuantity(), productQuantity, "Product Quantity is not correct!");
        Assert.assertEquals(manufacturingPage.getProductNameInList(inventoryTest.productName), inventoryTest.productName,
                "Product Name is list is not correct!");
        Assert.assertEquals(manufacturingPage.getToConsume(), toConsume, "To Consume is not correct!");
    }
}
