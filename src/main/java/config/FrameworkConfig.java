package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkConfig {

    private static FrameworkConfig instance;
    private static Properties properties;

    public FrameworkConfig(String configFilePath) {
        properties = getProperties(configFilePath);
    }

    public static FrameworkConfig getInstance() {
        if (instance == null) {
            instance = new FrameworkConfig("src/main/resources/frameworkconfig.properties");
        }
        return instance;
    }

    public String getProperty(final String keyName) {
        return properties.getProperty(keyName);
    }

    private Properties getProperties(final String filePath) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Cannot get properties from file, please check your file path!", e);
        }
        return prop;
    }
}

