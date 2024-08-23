package com.f2g.middleware.collab.controller;

import com.f2g.middleware.collab.dto.ConfigObjectDTO;
import com.f2g.middleware.collab.services.ConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    // EPC Config Endpoints
    @GetMapping("/epc/objects")
    public ResponseEntity<List<ConfigObjectDTO>> getEPCConfigObjects() {
        try {
            List<ConfigObjectDTO> configObjects = configService.getEPCConfigObjects();
            return ResponseEntity.ok(configObjects);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/epc/object")
    public ResponseEntity<String> updateEPCConfigObject(@RequestBody ConfigObjectDTO configObjectDTO) {
        try {
            configService.updateEPCConfigObject(configObjectDTO);
            return ResponseEntity.ok("EPC config object updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating EPC config object: " + e.getMessage());
        }
    }

    // ENB Config Endpoints
    @GetMapping("/enb/objects")
    public ResponseEntity<List<ConfigObjectDTO>> getENBConfigObjects() {
        try {
            List<ConfigObjectDTO> configObjects = configService.getENBConfigObjects();
            return ResponseEntity.ok(configObjects);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/enb/object")
    public ResponseEntity<String> updateENBConfigObject(@RequestBody ConfigObjectDTO configObjectDTO) {
        try {
            configService.updateENBConfigObject(configObjectDTO);
            return ResponseEntity.ok("ENB config object updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating ENB config object: " + e.getMessage());
        }
    }

    // UE Config Endpoints
    @GetMapping("/ue/objects")
    public ResponseEntity<List<ConfigObjectDTO>> getUEConfigObjects() {
        try {
            List<ConfigObjectDTO> configObjects = configService.getUEConfigObjects();
            return ResponseEntity.ok(configObjects);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/ue/object")
    public ResponseEntity<String> updateUEConfigObject(@RequestBody ConfigObjectDTO configObjectDTO) {
        try {
            configService.updateUEConfigObject(configObjectDTO);
            return ResponseEntity.ok("UE config object updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating UE config object: " + e.getMessage());
        }
    }
}