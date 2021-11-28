package driver;

import org.apache.commons.lang.StringUtils;

import java.util.stream.Stream;

public enum DriverType {

    CHROME("chrome"),
    SAFARI("safari");

    private String value;

    DriverType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DriverType of(String value) {
        if (StringUtils.isBlank(value)) {
            throw new RuntimeException("Blank string!");
        }
        return Stream.of(DriverType.values())
                .filter(driverType -> driverType.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid driver type, please check again!!!"));
    }
}
