package com.endava.petclinic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "qa"); // implicit "qa"
        InputStream resourceAsStream = EnvReader.class.getClassLoader().getResourceAsStream("env/" + env + ".properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException("Can't read property file", e);
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("baseUri");
    }

    public static String getBasePath() {
        return properties.getProperty("basePath");
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }

}
