package Pages.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Настройки для чтения данных с файла config.properties
 */

public class ConfigProperties {
    private static Properties PROPERTIES;
    static {
        PROPERTIES = new Properties();
        URL props = ClassLoader.getSystemResource("config.properties");
        try {
            PROPERTIES.load(props.openStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return PROPERTIES.getProperty(key);
    }
}
