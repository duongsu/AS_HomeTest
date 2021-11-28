package steps;

import org.testng.annotations.Test;
import pom.HomePage;
import pom.InventoryPage;
import pom.ProductsPage;
import utilities.RandomGeneration;

public class InventoryTest extends BaseTest {

    private HomePage homePage;
    private InventoryPage inventoryPage;
    private ProductsPage productsPage;
    private LoginTest loginTest = new LoginTest();
    public String productName;

    //------------------------------------------------------------------------------------------------------------------

    @Test(groups = {"P1"})
    public void createProductTest() {
        loginTest.loginTest();
        homePage = new HomePage(webDriver);
        inventoryPage = new InventoryPage(webDriver);
        productsPage = new ProductsPage(webDriver);

        homePage.clickInventoryLink();
        inventoryPage.clickProductsMenu();
        inventoryPage.clickProductsSubMenu();
        productsPage.clickCreateButton();
        productName = "Product " + new RandomGeneration().randomInRange(1, 1000);
        productsPage.inputProductName(productName);
        productsPage.clickSaveButton();
    }
}
