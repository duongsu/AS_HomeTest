package driver;

public class DriverManagerFactory {

    private DriverManagerFactory() {
    }

    private static AbstractDriverManager driverManager;

    public static AbstractDriverManager getExistingManager() {
        if (driverManager == null) {
            throw new RuntimeException("Need to initialize driver manager!");
        }
        return driverManager;
    }

    public static AbstractDriverManager getManager(DriverType type) {
        switch (type) {
            case SAFARI:
                driverManager = new SafariDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}
