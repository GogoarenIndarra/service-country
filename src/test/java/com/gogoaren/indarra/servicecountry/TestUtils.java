package com.gogoaren.indarra.servicecountry;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestUtils {

    public static String readFileAsString(InputStream inputStream) throws Exception {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    public static InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = TestUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
