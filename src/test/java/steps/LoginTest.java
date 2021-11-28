package steps;

import environment.EnvironmentHandler;
import org.testng.annotations.Test;
import pom.LoginPage;

public class LoginTest extends BaseTest {

    private EnvironmentHandler environmentHandler = EnvironmentHandler.getInstance();
    private LoginPage loginPage;

    //------------------------------------------------------------------------------------------------------------------

    @Test(groups = {"SmokeTest"})
    public void loginTest() {
        loginPage = new LoginPage(webDriver);
        webDriver.get(environmentHandler.getTestData("login", "url"));
        loginPage.inputUserNameAndPassword(environmentHandler.getTestData("login", "userName"),
                environmentHandler.getTestData("login", "password"));
        loginPage.clickLogInButton();
    }
}
