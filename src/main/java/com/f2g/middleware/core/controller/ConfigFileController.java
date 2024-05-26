package com.f2g.middleware.core.controller;

import com.f2g.middleware.core.service.ConfigFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/middleware-service/api/v1/config")
@AllArgsConstructor
public class ConfigFileController {


    private ConfigFileService configFileService;

    @GetMapping("/enb-config")
    public Map<String, String> getEnbConfig() {
        return configFileService.getEnbConfig();
    }

    @GetMapping("/epc-config")
    public Map<String, String> getEpcConfig() {
        return configFileService.getEpcConfig();
    }

    @GetMapping("/mbms-config")
    public Map<String, String> getMbmsConfig() {
        return configFileService.getMbmsConfig();
    }

    @GetMapping("/rb-config")
    public Map<String, String> getRbConfig() {
        return configFileService.getRbConfig();
    }

    @GetMapping("/rr-config")
    public Map<String, String> getRrConfig() {
        return configFileService.getRrConfig();
    }

    @GetMapping("/sib-config")
    public Map<String, String> getSibConfig() {
        return configFileService.getSibConfig();
    }

    @GetMapping("/ue-config")
    public Map<String, String> getUeConfig() {
        return configFileService.getUeConfig();
    }
}
