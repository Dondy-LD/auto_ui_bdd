package dataProviders;


import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class PropertyReader {
    Properties properties = new Properties();
    InputStream inputStream = null;

    public PropertyReader(String FilePath) {
        loadProperties(FilePath);
    }

    private void loadProperties(String FilePath) {
        try {
            inputStream = new FileInputStream(FilePath);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}