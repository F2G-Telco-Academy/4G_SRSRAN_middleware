package com.f2g.middleware.core.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Utilities {

    public static Map<String, String> extractUncommentedConfigValues(String inputFilePath) throws IOException {

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

    public static Map<String, String> extractCommentedConfigValues(String inputFilePath) throws IOException {

        Map<String, String> hashMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line by the "=" character and extract the value part
                String[] parts = line.split("=");
                if (parts.length == 2 && parts[0].startsWith("#") && !parts[0].startsWith("# ")) {
                    String value = parts[1].trim();
                    String key = parts[0].trim().replace("#", "");
                    hashMap.put(key,value);
                }
            }
        }
        return hashMap;
    }

    public static Map<String, String> extractAllConfigValues(String inputFilePath) throws IOException {

        Map<String, String> hashMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                // Split the line by the "=" character and extract the value part
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String value = parts[1].trim();
                    String key = parts[0].trim();
                    hashMap.put(key,value);
                }
            }
        }
        return hashMap;
    }

    public static void updateConfigValues(String filePath, Map<String, String> updatedConfigValues) throws IOException {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder();
        boolean keyFound;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                keyFound = false;

                for (Map.Entry<String, String> entry : updatedConfigValues.entrySet()) {
                    String key = entry.getKey();
                    if (line.startsWith(key) || line.startsWith("#" + key)) {
                        line = key + " = " + entry.getValue();
                        keyFound = true;
                        break;
                    }
                }

                fileContent.append(line).append(System.lineSeparator());
            }
        }

        for (Map.Entry<String, String> entry : updatedConfigValues.entrySet()) {
            if (!fileContent.toString().contains(entry.getKey())) {
                fileContent.append(entry.getKey()).append(" = ").append(entry.getValue()).append(System.lineSeparator());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent.toString());
        }
    }
}
