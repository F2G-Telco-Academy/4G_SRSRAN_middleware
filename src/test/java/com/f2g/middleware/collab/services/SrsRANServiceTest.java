package com.f2g.middleware.collab.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SrsRANServiceTest {

    @Autowired
    private SrsRANService srsRANService;


    @Test
    void testStopEPC() throws IOException {
        assertDoesNotThrow(() -> srsRANService.stopEPC());
    }

    @Test
    void testStopENB() throws IOException {
        assertDoesNotThrow(() -> srsRANService.stopENB());
    }

    @Test
    void testStopUE() throws IOException {
        assertDoesNotThrow(() -> srsRANService.stopUE());
    }

    @Test
    void testStartEPC() throws IOException, InterruptedException {
        String output = srsRANService.startEPC();
        assertNotNull(output); // Check that the output is not null
    }

    @Test
    void testStartENB() throws IOException, InterruptedException {
        String output = srsRANService.startENB();
        assertNotNull(output); // Check not null
    }

    @Test
    void testStartUE() throws IOException, InterruptedException {
        String output = srsRANService.startUE();
        assertNotNull(output); // Check that the output is not null
    }
}