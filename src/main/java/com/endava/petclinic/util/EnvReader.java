package com.endava.petclinic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

    private static final Properties properties = new Properties();

    static {
        InputStream resourceAsStream = EnvReader.class.getClassLoader().getResourceAsStream("env/qa.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
           throw new RuntimeException("Can't read property file", e);
        }
    }

    public static String getBaseUri(){
       return properties.getProperty("baseUri");
    }

    public static String getBasePath() {
        return properties.getProperty("basePath");
    }

}
