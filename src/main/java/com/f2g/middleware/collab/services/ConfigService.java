package com.f2g.middleware.collab.services;

import com.f2g.middleware.collab.dto.ConfigObjectDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigService {

    private static final String CONFIG_DIR = "/home/salam/.config/srsran/"; // Base config directory
//    private static final String CONFIG_DIR = "C:/Users/salam/Downloads/conf/"; // Base config directory
    private static final String EPC_CONFIG_PATH = CONFIG_DIR + "epc.conf";
    private static final String ENB_CONFIG_PATH = CONFIG_DIR + "enb.conf";
    private static final String UE_CONFIG_PATH = CONFIG_DIR + "ue.conf";

    // EPC Config Methods
    public List<ConfigObjectDTO> getEPCConfigObjects() throws IOException {
        return getConfigObjects(EPC_CONFIG_PATH);
    }

    public void updateEPCConfigObject(ConfigObjectDTO configObjectDTO) throws IOException {
        updateConfigObject(EPC_CONFIG_PATH, configObjectDTO);
    }

    // ENB Config Methods
    public List<ConfigObjectDTO> getENBConfigObjects() throws IOException {
        return getConfigObjects(ENB_CONFIG_PATH);
    }

    public void updateENBConfigObject(ConfigObjectDTO configObjectDTO) throws IOException {
        updateConfigObject(ENB_CONFIG_PATH, configObjectDTO);
    }

    // UE Config Methods
    public List<ConfigObjectDTO> getUEConfigObjects() throws IOException {
        return getConfigObjects(UE_CONFIG_PATH);
    }

    public void updateUEConfigObject(ConfigObjectDTO configObjectDTO) throws IOException {
        updateConfigObject(UE_CONFIG_PATH, configObjectDTO);
    }

    // Helper Methods
    private List<ConfigObjectDTO> getConfigObjects(String configPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(configPath));
        List<ConfigObjectDTO> configObjects = new ArrayList<>();
        String currentSection = "";

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("[") && line.endsWith("]")) {
                currentSection = line.substring(1, line.length() - 1);
            } else if (line.contains("=")) {
                String[] parts = line.split("=");
                String label = parts[0].trim();
                String value;
                if (parts.length >= 2) {
                    value = parts[1].trim().replaceAll(";", "");
                } else {
                    value = "";
                }

                boolean isActive = !line.startsWith("#");
                if (!isActive) {
                    label = label.substring(1);
                }
                configObjects.add(new ConfigObjectDTO(label, value, isActive, configPath, currentSection));
            }
        }

        return configObjects;
    }

    private void updateConfigObject(String configPath, ConfigObjectDTO configObjectDTO) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(configPath));
        boolean found = false;

        String currentSection = ""; // Keep track of the current section

        for (int i = 0; i < lines.size(); i++) {

            String line = lines.get(i).trim();

            if (line.startsWith("[") && line.endsWith("]")) {
                currentSection = line.substring(1, line.length() - 1);
            } else if (line.matches("^" + configObjectDTO.label + "\\s*=.*") ||
                    (line.matches("^#" + configObjectDTO.label + "\\s*=.*"))) {

                // Check if the current section matches the DTO's section
                if (configObjectDTO.section != null && configObjectDTO.section.equals(currentSection)) {
                    String newLine = configObjectDTO.label + " = " + configObjectDTO.value;
                    if (!configObjectDTO.isActive) {
                        newLine = "#" + newLine;
                    }
                    lines.set(i, newLine);
                    found = true;
                    break;
                }

            }
        }

        if (!found) {
            // Add new parameter if not found
            if (configObjectDTO.section == null || configObjectDTO.section.equals(currentSection)) {
                String newLine = configObjectDTO.label + " = " + configObjectDTO.value;
                if (!configObjectDTO.isActive) {
                    newLine = "#" + newLine;
                }
                lines.add(newLine);
            }
        }

        Files.write(Paths.get(configPath), lines);
    }

}