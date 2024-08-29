package com.f2g.middleware.collab.controller;

import com.f2g.middleware.collab.services.InternetConnectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InternetConnectionController.class)
class InternetConnectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InternetConnectionService internetConnectionService;

    @Test
    void testCheckInternetConnection_Available() throws Exception {
        when(internetConnectionService.isInternetAvailable()).thenReturn(true);

        mockMvc.perform(get("/connection/check"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));
    }

    @Test
    void testCheckInternetConnection_Unavailable() throws Exception {
        when(internetConnectionService.isInternetAvailable()).thenReturn(false);

        mockMvc.perform(get("/connection/check"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("false"));
    }
}