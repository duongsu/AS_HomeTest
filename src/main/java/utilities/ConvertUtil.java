package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConvertUtil {

    private ConvertUtil() {
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertJsonFileToObject(final String filePath, final Class<T> clazz) {
        try {
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
    }

    public static Map<String, String> convertJsonFileToMap(final String filePath) {
        try {
            return mapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
    }

    public static <T> T convertGenericObjectToObject(Object genericObject, final Class<T> clazz) {
        try {
            final byte[] bytes = mapper.writeValueAsBytes(genericObject);
            return mapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
    }
}
