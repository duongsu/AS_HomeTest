package driver;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriverManager {

    static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    abstract void startService();

    abstract void stopService();

    abstract void createDriver();

    abstract void createRemoteDriver();

    public void quitDriver() {
        if (null != driverThreadLocal.get()) {
            stopService();
            driverThreadLocal.get().quit();
            driverThreadLocal.set(null);
        }

    }

    public WebDriver getDriver() {
        if (null == driverThreadLocal.get()) {
            startService();
            createDriver();
        }
        return driverThreadLocal.get();
    }

}
