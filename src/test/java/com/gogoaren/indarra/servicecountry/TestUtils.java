package com.gogoaren.indarra.servicecountry;

import org.springframework.util.Assert;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {
    public static String readFileAsString(String file) throws Exception {
        Assert.notNull(file, "file does not exist");
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
