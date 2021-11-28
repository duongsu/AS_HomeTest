package environment;

import model.GenericModel;
import utilities.ConvertUtil;

import java.util.Map;

public class EnvironmentHandler {
    private static final String ENVIRONMENT_PARAM = "environment";
    private static EnvironmentHandler instance;
    private static GenericModel testDataGenericModel;

    public EnvironmentType environment;

    private EnvironmentHandler(EnvironmentType environmentType) {
        String currentEnvParam = System.getProperty(ENVIRONMENT_PARAM);
        if (currentEnvParam != null) {
            environment = convertEnvironmentStringToEnvironmentType((currentEnvParam));
        } else {
            if (environmentType != null) {
                environment = environmentType;
            } else {
                environment = EnvironmentType.STAGING;
            }
        }
        try {
            testDataGenericModel = ConvertUtil.convertJsonFileToObject(environment.getValue(), GenericModel.class);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to initializing test environment! Please check your test data file!", e);
        }
    }

    public static EnvironmentHandler getInstance(EnvironmentType environmentType) {
        if (instance == null) {
            instance = new EnvironmentHandler(environmentType);
        }
        return instance;
    }

    public static EnvironmentHandler getInstance() {
        if (instance == null) {
            instance = new EnvironmentHandler(null);
        }
        return instance;
    }

    private EnvironmentType convertEnvironmentStringToEnvironmentType(String s) {
        EnvironmentType environmentType;
        try {
            environmentType = EnvironmentType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(String.format("\"The test environment [%s] doesn't exists. Please input the correct one! Error >>>> \"", s) + e);
        }
        return environmentType;
    }

    @SuppressWarnings("unchecked")
    public String getTestData(String component, String key) {
        Map<String, Object> data = (Map<String, Object>) testDataGenericModel.getProperties().get(component);
        return data.get(key).toString();
    }

    public Map<String, Object> getTestDataToMap(String component) {
        Map<String, Object> data = (Map<String, Object>) testDataGenericModel.getProperties().get(component);
        return data;
    }
}
