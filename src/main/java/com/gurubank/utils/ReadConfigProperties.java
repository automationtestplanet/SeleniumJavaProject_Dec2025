package com.gurubank.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ReadConfigProperties {

    public static Properties testProperties = new Properties();
    private static final String TEST_PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/config.properties";

    static {
        try {
            testProperties.load(new FileInputStream(TEST_PROPERTIES_FILE_PATH));
        } catch (Exception e) {
            System.out.println("Exception occurred while loading properties file : " + e.getMessage());
        }
    }


    public static String getConfigPropertyValue(String key) {
        String value = null;
        try {
            value = testProperties.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception occurred while reading property value for key : " + key + " - " + e.getMessage());
        }
        return value;
    }

    public static void setConfigPropertyValue(String key, String value) {
        try {
            testProperties.setProperty(key, value);
            testProperties.store(new FileOutputStream(TEST_PROPERTIES_FILE_PATH), "Updated by: " + System.getProperty("user.name"));
        } catch (Exception e) {
            System.out.println("Exception occurred while setting property value for key : " + key + " - " + e.getMessage());
        }
    }

}
