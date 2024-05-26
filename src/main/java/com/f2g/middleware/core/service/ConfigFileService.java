package com.f2g.middleware.core.service;

import com.f2g.middleware.core.utils.Utilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ConfigFileService {

    @Value("file:${enb_config.file.path}")
    private Resource enbConfigFIle;

    @Value("file:${epc_config.file.path}")
    private Resource epcConfigFIle;

    @Value("file:${mbms_config.file.path}")
    private Resource mbmsConfigFIle;

    @Value("file:${rb_config.file.path}")
    private Resource rbConfigFIle;

    @Value("file:${rr_config.file.path}")
    private Resource rrConfigFIle;

    @Value("file:${sib_config.file.path}")
    private Resource sibConfigFIle;

    @Value("file:${ue_config.file.path}")
    private Resource ueConfigFIle;

    public Map<String, String> getEnbConfig() {

        Map<String, String> config;

        try {
            config = Utilities.extractConfigValues((enbConfigFIle.getURI().getPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getEpcConfig() {

        Map<String, String> config;

        try {
            config = Utilities.extractConfigValues((epcConfigFIle.getURI().getPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getMbmsConfig() {

        Map<String, String> config;

        try {
            config = Utilities.extractConfigValues((mbmsConfigFIle.getURI().getPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getRbConfig() {

        Map<String, String> config;

        try {
            config = Utilities.extractConfigValues((rbConfigFIle.getURI().getPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getRrConfig() {

        Map<String, String> config;

        try {
            config = Utilities.extractConfigValues((rrConfigFIle.getURI().getPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getSibConfig() {

        Map<String, String> config;

        try {
            config = Utilities.extractConfigValues((sibConfigFIle.getURI().getPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getUeConfig() {

        Map<String, String> config;

        try {
            config = Utilities.extractConfigValues((ueConfigFIle.getURI().getPath()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }


}
