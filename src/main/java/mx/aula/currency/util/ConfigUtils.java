package mx.aula.currency.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static final String CONFIG_NAME = "config.properties";
    private static Properties cachedProperties;

    public ConfigUtils() {
    }

    private static Properties getProperties() {
        if (cachedProperties == null) {
            cachedProperties = new Properties();
            try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream(CONFIG_NAME)) {
                if (input == null) {
                    throw new IOException("[WARNING] No se encontó el archivo de configuración " + CONFIG_NAME);
                }
                cachedProperties.load(input);
            } catch (Exception e) {
                System.out.println("[WARNING] : " + e.getMessage());
            }
        }
        return cachedProperties;
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}
