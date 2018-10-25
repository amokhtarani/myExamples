package com.wellsfargo.messaging.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by u554732 on 12/14/16.
 */
public class Configuration {

    private final static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private static Properties propertiesFile;
    private static String CONFIG_FILE = "configFile";


    public static String getProp(String key) {

        if(propertiesFile == null) {
            String propSource = System.getProperty(CONFIG_FILE);
            if(propSource == null) {
                logger.error("No configuration file is specified");
            }
            propertiesFile = new Properties();
            try {
                ConfigureInputSource input = new ConfigureInputSource(propSource);
                propertiesFile.load(input.getInputStream());

            } catch (FileNotFoundException e) {
               logger.error("File not found");
            } catch(IOException e) {
                logger.error("Got an exception while loading properties file");

            }
        }
        String value = propertiesFile.getProperty(key);

        return value;
    }


}
