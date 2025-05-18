package mx.aula.currency.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigUtils {
    private static final String CONFIG_NAME = "config.properties";
    private static Properties cachedProperties;

    private static final Logger logger = Logger.getLogger(ConfigUtils.class.getName());

    private static Properties getProperties() {
        if (cachedProperties == null) {
            cachedProperties = new Properties();
            try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream(CONFIG_NAME)) {
                if (input == null) {
                    throw new IOException("No se encontró el archivo de configuración " + CONFIG_NAME);
                }
                cachedProperties.load(input);
            } catch (Exception e) {
                logger.severe("[getProperties] : "+e.getMessage());
            }
        }
        return cachedProperties;
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}
