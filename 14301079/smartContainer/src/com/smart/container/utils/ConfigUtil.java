package com.smart.container.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	private static Properties properties = new Properties();
    
    static {
        try {
        	properties.load(new FileInputStream(".//container.config"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
