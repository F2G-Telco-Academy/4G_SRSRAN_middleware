package com.f2g.middleware.collab.services;

import com.f2g.middleware.collab.dto.ConfigObjectDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
class ConfigServiceTest {

    @Autowired
    private ConfigService configService;

    @Test
    void testGetEPCConfigObjects() throws IOException {
        // Create temporary test file
        Path tempFile = Files.createTempFile("epc", ".conf");
        String testConfig = "[mme]\n" +
                "mme_code = 0x1a\n" +
                "mme_group = 0x0001\n" +
                "#tac = 0x0007";
        Files.writeString(tempFile, testConfig);

        List<ConfigObjectDTO> configObjects = configService.getConfigObjects(tempFile.toString());

        assertEquals(3, configObjects.size());
        assertEquals("mme_code", configObjects.get(0).label);
        assertEquals("0x1a", configObjects.get(0).value);
        assertTrue(configObjects.get(0).isActive);
        assertEquals("mme", configObjects.get(0).section);
        assertEquals("tac", configObjects.get(2).label);
        assertFalse(configObjects.get(2).isActive);

        // Delete the temporary file
        Files.delete(tempFile);
    }

    @Test
    void testUpdateEPCConfigObject() throws IOException {
        // Create a temp test file
        Path tempFile = Files.createTempFile("epc", ".conf");
        String testConfig = "[mme]\n" +
                "mme_code = 0x1a\n" +
                "#mme_group = 0x0001\n";
        Files.writeString(tempFile, testConfig);

        // Update an existing param
        ConfigObjectDTO updateExisting = new ConfigObjectDTO("mme_code", "0x1b", true, tempFile.toString(), "mme");
        configService.updateConfigObject(tempFile.toString(), updateExisting);
        List<ConfigObjectDTO> configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("0x1b", configObjectsAfterUpdate.get(0).value);

        // Activate a commented-out parameter
        ConfigObjectDTO activateParameter = new ConfigObjectDTO("mme_group", "0x0002", true, tempFile.toString(), "mme");
        configService.updateConfigObject(tempFile.toString(), activateParameter);
        configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("0x0002", configObjectsAfterUpdate.get(1).value);
        assertTrue(configObjectsAfterUpdate.get(1).isActive);

        // Add a new parametettt
        ConfigObjectDTO addNewParameter = new ConfigObjectDTO("new_parameter", "new_value", true, tempFile.toString(), "mme");
        configService.updateConfigObject(tempFile.toString(), addNewParameter);
        configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());

        // Access the new parameter using configObjectsAferUpate.size() - 1
        assertEquals("new_value", configObjectsAfterUpdate.get(configObjectsAfterUpdate.size() - 1).value);

        // Delete the temprary file
        Files.delete(tempFile);
    }

    @Test
    void testGetENBConfigObjects() throws IOException {
        // Create temporary test file..
        Path tempFile = Files.createTempFile("enb", ".conf");
        String testConfig = "[enb]\n" +
                "enb_id = 0x19B\n" +
                "mcc = 001\n" +
                "#nof_ports = 2";
        Files.writeString(tempFile, testConfig);

        List<ConfigObjectDTO> configObjects = configService.getConfigObjects(tempFile.toString());

        assertEquals(3, configObjects.size());
        assertEquals("enb_id", configObjects.get(0).label);
        assertEquals("0x19B", configObjects.get(0).value);
        assertTrue(configObjects.get(0).isActive);
        assertEquals("enb", configObjects.get(0).section);
        assertEquals("nof_ports", configObjects.get(2).label);
        assertFalse(configObjects.get(2).isActive);

        // Delete the temp file
        Files.delete(tempFile);
    }

    @Test
    void testUpdateENBConfigObject() throws IOException {
        // Createing temporary test file
        Path tempFile = Files.createTempFile("enb", ".conf");
        String testConfig = "[enb]\n" +
                "enb_id = 0x19B\n" +
                "#mcc = 001\n";
        Files.writeString(tempFile, testConfig);

        // Update existing parameter
        ConfigObjectDTO updateExisting = new ConfigObjectDTO("enb_id", "0x19C", true, tempFile.toString(), "enb");
        configService.updateConfigObject(tempFile.toString(), updateExisting);
        List<ConfigObjectDTO> configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("0x19C", configObjectsAfterUpdate.get(0).value);

        // Activate a commented-out parameter
        ConfigObjectDTO activateParameter = new ConfigObjectDTO("mcc", "002", true, tempFile.toString(), "enb");
        configService.updateConfigObject(tempFile.toString(), activateParameter);
        configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("002", configObjectsAfterUpdate.get(1).value);
        assertTrue(configObjectsAfterUpdate.get(1).isActive);

        // Add a new parameter
        ConfigObjectDTO addNewParameter = new ConfigObjectDTO("new_parameter", "new_value", true, tempFile.toString(), "enb");
        configService.updateConfigObject(tempFile.toString(), addNewParameter);
        configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("new_value", configObjectsAfterUpdate.get(configObjectsAfterUpdate.size() - 1).value); // Access the new parameter using size() - 1

        // Delete the temporary file
        Files.delete(tempFile);
    }

    @Test
    void testGetUEConfigObjects() throws IOException {
        // Create a temporary test file
        Path tempFile = Files.createTempFile("ue", ".conf");
        String testConfig = "[rf]\n" +
                "freq_offset = 0\n" +
                "tx_gain = 80\n" +
                "#rx_gain = 40";
        Files.writeString(tempFile, testConfig);

        List<ConfigObjectDTO> configObjects = configService.getConfigObjects(tempFile.toString());

        assertEquals(3, configObjects.size());
        assertEquals("freq_offset", configObjects.get(0).label);
        assertEquals("0", configObjects.get(0).value);
        assertTrue(configObjects.get(0).isActive);
        assertEquals("rf", configObjects.get(0).section);
        assertEquals("rx_gain", configObjects.get(2).label);
        assertFalse(configObjects.get(2).isActive);

        // Delete the temporary file
        Files.delete(tempFile);
    }

    @Test
    void testUpdateUEConfigObject() throws IOException {
        // Create a temporary test file
        Path tempFile = Files.createTempFile("ue", ".conf");
        String testConfig = "[rf]\n" +
                "freq_offset = 0\n" +
                "#tx_gain = 80\n";
        Files.writeString(tempFile, testConfig);

        // Update an existing parameter
        ConfigObjectDTO updateExisting = new ConfigObjectDTO("freq_offset", "1", true, tempFile.toString(), "rf");
        configService.updateConfigObject(tempFile.toString(), updateExisting);
        List<ConfigObjectDTO> configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("1", configObjectsAfterUpdate.get(0).value);

        // Activate a commented-out parameter
        ConfigObjectDTO activateParameter = new ConfigObjectDTO("tx_gain", "81", true, tempFile.toString(), "rf");
        configService.updateConfigObject(tempFile.toString(), activateParameter);
        configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("81", configObjectsAfterUpdate.get(1).value);
        assertTrue(configObjectsAfterUpdate.get(1).isActive);

        // Add a new parameter
        ConfigObjectDTO addNewParameter = new ConfigObjectDTO("new_parameter", "new_value", true, tempFile.toString(), "rf");
        configService.updateConfigObject(tempFile.toString(), addNewParameter);
        configObjectsAfterUpdate = configService.getConfigObjects(tempFile.toString());
        assertEquals("new_value", configObjectsAfterUpdate.get(configObjectsAfterUpdate.size() - 1).value); // Access the new parameter using size() - 1

        // Delete the temporary file
        Files.delete(tempFile);
    }
}