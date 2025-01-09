package Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger logg = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_CONFIG_FILE= "config/global.properties";
    //C:\Users\91762\IdeaProjects\SDETVS25Jan\src\test\java\resources\config\global.properties
    private static Properties properties;




    public static void initialize() throws IOException {
        properties =Config.loadProperties();
        logg.info("Properties are loaded successfully");

        for (String key: properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key))
                properties.setProperty(key,System.getProperty(key));
        }

        for(String key:properties.stringPropertyNames()){
            logg.info("{}={}",key,properties.getProperty(key));
        }


    }

    public static Properties loadProperties() throws IOException {
        properties = new Properties();

        // Check for an environment variable to override the config file path
        String configFilePath = System.getenv("CONFIG_PATH");
        System.out.println(System.getenv("HUB_HOST"));
        logg.info("Config file path from environment variable: {}", configFilePath);
        if (configFilePath == null || configFilePath.isEmpty()) {
            // Fallback to default classpath resource if no environment variable
            logg.info("Using default config file: {}", DEFAULT_CONFIG_FILE);
            try (InputStream fileInputStream = Config.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE)) {
                if (fileInputStream == null) {
                    throw new IOException("Configuration file '" + DEFAULT_CONFIG_FILE + "' not found in the classpath.");
                }
                properties.load(fileInputStream);
            }
        } else {
            logg.info("Using custom config file from environment variable: {}", configFilePath);
            try (InputStream fileInputStream = new FileInputStream(configFilePath)) {
                properties.load(fileInputStream);
            }
        }

        return properties;
    }


        public static String getProperty(String key){
            logg.info("Value of {} is {}",key,properties.getProperty(key));
            return properties.getProperty(key);
        }



}
