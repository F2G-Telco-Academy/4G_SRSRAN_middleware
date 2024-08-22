package com.f2g.middleware.core.controller;

import com.f2g.middleware.core.enums.ConfigEnum;
import com.f2g.middleware.core.service.ConfigFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/middleware-service/api/v1/config")
@AllArgsConstructor
public class ConfigFileController {


    private ConfigFileService configFileService;

    @GetMapping("/enb-config/{configEnum}")
    public Map<String, String> getEnbConfig(@PathVariable ConfigEnum configEnum) {
        return configFileService.getEnbConfig(configEnum);
    }

    @GetMapping("/epc-config/{configEnum}")
    public Map<String, String> getEpcConfig(@PathVariable ConfigEnum configEnum) {
        return configFileService.getEpcConfig(configEnum);
    }

    @GetMapping("/mbms-config/{configEnum}")
    public Map<String, String> getMbmsConfig(@PathVariable ConfigEnum configEnum) {
        return configFileService.getMbmsConfig(configEnum);
    }

    @GetMapping("/rb-config/{configEnum}")
    public Map<String, String> getRbConfig(@PathVariable ConfigEnum configEnum) {
        return configFileService.getRbConfig(configEnum);
    }

    @GetMapping("/rr-config/{configEnum}")
    public Map<String, String> getRrConfig(@PathVariable ConfigEnum configEnum) {
        return configFileService.getRrConfig(configEnum);
    }

    @GetMapping("/sib-config/{configEnum}")
    public Map<String, String> getSibConfig(@PathVariable ConfigEnum configEnum) {
        return configFileService.getSibConfig(configEnum);
    }

    @GetMapping("/ue-config/{configEnum}")
    public Map<String, String> getUeConfig(@PathVariable ConfigEnum configEnum) {
        return configFileService.getUeConfig(configEnum);
    }

    @PostMapping("epc-config")
    public Map<String, String> updateEpcConfig(@RequestBody Map<String, String> config) {
        return configFileService.updateEpcConfig(config);
    }
}
