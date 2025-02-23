package storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private Properties properties;

    public ConfigManager(String propertiesFilePath) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new IllegalArgumentException(propertiesFilePath +" file cannot be found");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
