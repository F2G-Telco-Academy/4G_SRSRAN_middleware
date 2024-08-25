package com.f2g.middleware.collab.controller;

import com.f2g.middleware.collab.dto.ConfigObjectDTO;
import com.f2g.middleware.collab.services.ConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConfigController.class)
class ConfigControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigService configService;

    // EPC Config Endpoints Tests
    @Test
    void testGetEPCConfigObjects() throws Exception {
        ConfigObjectDTO configObject1 = new ConfigObjectDTO("mme_code", "0x1a", true, "epc.conf", "mme");
        ConfigObjectDTO configObject2 = new ConfigObjectDTO("mme_group", "0x0001", true, "epc.conf", "mme");
        List<ConfigObjectDTO> mockConfigObjects = Arrays.asList(configObject1, configObject2);

        when(configService.getEPCConfigObjects()).thenReturn(mockConfigObjects);

        mockMvc.perform(get("/config/epc/objects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].label", is("mme_code")))
                .andExpect(jsonPath("$[1].value", is("0x0001")));
    }

    @Test
    void testUpdateEPCConfigObject() throws Exception {
        ConfigObjectDTO configObject = new ConfigObjectDTO("mme_code", "0x1b", true, "epc.conf", "mme");

        doNothing().when(configService).updateEPCConfigObject(any(ConfigObjectDTO.class));

        mockMvc.perform(put("/config/epc/object")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"label\":\"mme_code\", \"value\":\"0x1b\", \"isActive\":true, \"filename\":\"epc.conf\", \"section\":\"mme\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("EPC config object updated successfully."));
    }

    // ENB Config Endpoints Tests
    @Test
    void testGetENBConfigObjects() throws Exception {
        ConfigObjectDTO configObject1 = new ConfigObjectDTO("enb_id", "0x19B", true, "enb.conf", "enb");
        ConfigObjectDTO configObject2 = new ConfigObjectDTO("mcc", "001", true, "enb.conf", "enb");
        List<ConfigObjectDTO> mockConfigObjects = Arrays.asList(configObject1, configObject2);

        when(configService.getENBConfigObjects()).thenReturn(mockConfigObjects);

        mockMvc.perform(get("/config/enb/objects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].label", is("enb_id")))
                .andExpect(jsonPath("$[1].value", is("001")));
    }

    @Test
    void testUpdateENBConfigObject() throws Exception {
        ConfigObjectDTO configObject = new ConfigObjectDTO("enb_id", "0x19C", true, "enb.conf", "enb");

        doNothing().when(configService).updateENBConfigObject(any(ConfigObjectDTO.class));

        mockMvc.perform(put("/config/enb/object")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"label\":\"enb_id\", \"value\":\"0x19C\", \"isActive\":true, \"filename\":\"enb.conf\", \"section\":\"enb\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("ENB config object updated successfully."));
    }

    // UE Config Endpoints Tests
    @Test
    void testGetUEConfigObjects() throws Exception {
        ConfigObjectDTO configObject1 = new ConfigObjectDTO("freq_offset", "0", true, "ue.conf", "rf");
        ConfigObjectDTO configObject2 = new ConfigObjectDTO("tx_gain", "80", true, "ue.conf", "rf");
        List<ConfigObjectDTO> mockConfigObjects = Arrays.asList(configObject1, configObject2);

        when(configService.getUEConfigObjects()).thenReturn(mockConfigObjects);

        mockMvc.perform(get("/config/ue/objects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].label", is("freq_offset")))
                .andExpect(jsonPath("$[1].value", is("80")));
    }

    @Test
    void testUpdateUEConfigObject() throws Exception {
        ConfigObjectDTO configObject = new ConfigObjectDTO("freq_offset", "1", true, "ue.conf", "rf");

        doNothing().when(configService).updateUEConfigObject(any(ConfigObjectDTO.class));

        mockMvc.perform(put("/config/ue/object")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"label\":\"freq_offset\", \"value\":\"1\", \"isActive\":true, \"filename\":\"ue.conf\", \"section\":\"rf\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("UE config object updated successfully."));
    }
}