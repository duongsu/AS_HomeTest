package environment;

public enum EnvironmentType {

    STAGING("src/test/resources/testdataassets/staging_test_data.json"),
    DEMO("src/test/resources/testdataassets/demo_test_data.json");

    private String value;

    EnvironmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
