import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    private final String filePath = "src/test/resources/Config.properties";

    public ConfigReader() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            properties = new Properties();
            try {
                properties.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Properties file not found at path : " + filePath);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException ignore) {
            }
        }
    }

    public String getConfigProperty(String propertyKey) {
        String key = properties.getProperty(propertyKey);
        return key;
    }
}
