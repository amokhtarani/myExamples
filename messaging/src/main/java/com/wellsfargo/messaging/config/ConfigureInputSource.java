package com.wellsfargo.messaging.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by u554732 on 12/14/16.
 */
public class ConfigureInputSource {

    private final static Logger logger = LoggerFactory.getLogger(ConfigureInputSource.class);
    private String inSource;

    public ConfigureInputSource(String inSource) {
        this.inSource = inSource;
    }
    public InputStream getInputStream() {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(inSource);
        } catch (Exception e) {
            logger.error("Properties file not found", inSource);
        }
    return is;
    }
}
