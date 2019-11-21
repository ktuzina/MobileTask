package com.training.task.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyHandler {

    private static final String APP_PROPERTIES = "application.properties";

    private static Properties pr = new Properties();

    static {
        try {
            FileInputStream inp = new FileInputStream(Objects.requireNonNull(PropertyHandler.class.getClassLoader().getResource(APP_PROPERTIES)).getPath());
            pr.load(inp);
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return pr.getProperty(key);
    }

}
