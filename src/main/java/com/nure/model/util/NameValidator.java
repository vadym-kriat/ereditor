package com.nure.model.util;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vadim_ on 31.01.2018.
 */
public class NameValidator {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(NameValidator.class.
                    getClassLoader().getResourceAsStream("conf/text.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean nameIsValid(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public static boolean schemeNameIsValid(String text) {
        return nameIsValid(properties.getProperty("validation.regex.scheme"), text);
    }

    public static boolean tableNameIsValid(String text) {
        return nameIsValid(properties.getProperty("validation.regex.table"), text);
    }

    public static boolean columnNameIsValid(String text) {
        return nameIsValid(properties.getProperty("validation.regex.column"), text);
    }
}
