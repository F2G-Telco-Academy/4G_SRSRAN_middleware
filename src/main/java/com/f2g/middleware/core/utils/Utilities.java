package com.f2g.middleware.core.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Utilities {

    public static Map<String, String> extractConfigValues(String inputFilePath) throws IOException {

        Map<String, String> hashMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line by the "=" character and extract the value part
                String[] parts = line.split("=");
                if (parts.length == 2 && !parts[0].startsWith("#")) {
                    String value = parts[1].trim();
                    String key = parts[0].trim();
                    hashMap.put(key,value);
                }
            }
        }
        return hashMap;
    }

    public static Map<String, String> updateConfigValues(String filePath, Map<String, String> updatedConfigValues){
        //TODO: implement method
        return updatedConfigValues;
    }
}
