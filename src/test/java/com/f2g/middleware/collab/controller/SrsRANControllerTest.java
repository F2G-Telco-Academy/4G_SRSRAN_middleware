package com.f2g.middleware.collab.controller;

import com.f2g.middleware.collab.services.SrsRANService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SrsRANController.class)
class SrsRANControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SrsRANService srsRANService;

    @Test
    void testExecuteCommand() throws Exception {
        when(srsRANService.executeCommand(any(String.class))).thenReturn("Command output");

        mockMvc.perform(post("/api/execute")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("test command"))
                .andExpect(status().isOk())
                .andExpect(content().string("Command output"));
    }

    @Test
    void testStartEPC() throws Exception {
        when(srsRANService.startEPC()).thenReturn("EPC started");

        mockMvc.perform(post("/api/epc/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("EPC started"));
    }

    @Test
    void testStartENB() throws Exception {
        when(srsRANService.startENB()).thenReturn("ENB started");

        mockMvc.perform(post("/api/enb/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("ENB started"));
    }

    @Test
    void testStartUE() throws Exception {
        when(srsRANService.startUE()).thenReturn("UE started");

        mockMvc.perform(post("/api/ue/start"))
                .andExpect(status().isOk())
                .andExpect(content().string("UE started"));
    }

    @Test
    void testGetEPCStatus() throws Exception {
        when(srsRANService.isEPCRunning()).thenReturn(true);

        mockMvc.perform(get("/api/epc/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Running"));
    }

    @Test
    void testGetENBStatus() throws Exception {
        when(srsRANService.isENBRunning()).thenReturn(false);

        mockMvc.perform(get("/api/enb/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Not Running"));
    }

    @Test
    void testGetUEStatus() throws Exception {
        when(srsRANService.isUERunning()).thenReturn(true);

        mockMvc.perform(get("/api/ue/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("Running"));
    }

    @Test
    void testStopEPC() throws Exception {
        doNothing().when(srsRANService).stopEPC();

        mockMvc.perform(post("/api/epc/stop"))
                .andExpect(status().isOk())
                .andExpect(content().string("EPC stopped successfully."));
    }

    @Test
    void testStopENB() throws Exception {
        doNothing().when(srsRANService).stopENB();

        mockMvc.perform(post("/api/enb/stop"))
                .andExpect(status().isOk())
                .andExpect(content().string("ENB stopped successfully."));
    }

    @Test
    void testStopUE() throws Exception {
        doNothing().when(srsRANService).stopUE();

        mockMvc.perform(post("/api/ue/stop"))
                .andExpect(status().isOk())
                .andExpect(content().string("UE stopped successfully."));
    }
}