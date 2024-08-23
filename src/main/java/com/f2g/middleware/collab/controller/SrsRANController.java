package com.f2g.middleware.collab.controller;

import com.f2g.middleware.collab.services.SrsRANService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SrsRANController {

    private final SrsRANService srsRANService;

    public SrsRANController(SrsRANService srsRANService) {
        this.srsRANService = srsRANService;
    }

    // Executing custom command
    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody String command) {
        try {
            String output = srsRANService.executeCommand(command);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error executing command: " + e.getMessage());
        }
    }

    // Run services
    @PostMapping("/epc/start")
    public ResponseEntity<String> startEPC() {
        try {
            String output = srsRANService.startEPC();
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error starting EPC: " + e.getMessage());
        }
    }

    @PostMapping("/enb/start")
    public ResponseEntity<String> startENB() {
        try {
            String output = srsRANService.startENB();
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error starting ENB: " + e.getMessage());
        }
    }

    @PostMapping("/ue/start")
    public ResponseEntity<String> startUE() {
        try {
            String output = srsRANService.startUE();
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error starting UE: " + e.getMessage());
        }
    }

    // Status of services
    @GetMapping("/epc/status")
    public ResponseEntity<String> getEPCStatus() {
        try {
            boolean isRunning = srsRANService.isEPCRunning();
            return ResponseEntity.ok(isRunning ? "Running" : "Not Running");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking EPC status: " + e.getMessage());
        }
    }

    @GetMapping("/enb/status")
    public ResponseEntity<String> getENBStatus() {
        try {
            boolean isRunning = srsRANService.isENBRunning();
            return ResponseEntity.ok(isRunning ? "Running" : "Not Running");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking ENB status: " + e.getMessage());
        }
    }

    @GetMapping("/ue/status")
    public ResponseEntity<String> getUEStatus() {
        try {
            boolean isRunning = srsRANService.isUERunning();
            return ResponseEntity.ok(isRunning ? "Running" : "Not Running");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking UE status: " + e.getMessage());
        }
    }

    // Stopping services
    @PostMapping("/epc/stop")
    public ResponseEntity<String> stopEPC() {
        try {
            srsRANService.stopEPC();
            return ResponseEntity.ok("EPC stopped successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error stopping EPC: " + e.getMessage());
        }
    }

    @PostMapping("/enb/stop")
    public ResponseEntity<String> stopENB() {
        try {
            srsRANService.stopENB();
            return ResponseEntity.ok("ENB stopped successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error stopping ENB: " + e.getMessage());
        }
    }

    @PostMapping("/ue/stop")
    public ResponseEntity<String> stopUE() {
        try {
            srsRANService.stopUE();
            return ResponseEntity.ok("UE stopped successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error stopping UE: " + e.getMessage());
        }
    }
}