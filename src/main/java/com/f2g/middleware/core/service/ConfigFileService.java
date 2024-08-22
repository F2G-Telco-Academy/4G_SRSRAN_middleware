package com.f2g.middleware.core.service;

import com.f2g.middleware.core.enums.ConfigEnum;
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

    public Map<String, String> getEnbConfig(ConfigEnum configEnum) {

        Map<String, String> config;

        try {

            config = switch (configEnum) {
                case COMMENTED -> Utilities.extractCommentedConfigValues((enbConfigFIle.getURI().getPath()));
                case UNCOMMENTED -> Utilities.extractUncommentedConfigValues((enbConfigFIle.getURI().getPath()));
                case ALL -> Utilities.extractAllConfigValues((enbConfigFIle.getURI().getPath()));
                default -> throw new IllegalArgumentException("Invalid configEnum: " + configEnum);
            };

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getEpcConfig(ConfigEnum configEnum) {

        Map<String, String> config;

        try {

            config = switch (configEnum) {
                case COMMENTED -> Utilities.extractCommentedConfigValues((epcConfigFIle.getURI().getPath()));
                case UNCOMMENTED -> Utilities.extractUncommentedConfigValues((epcConfigFIle.getURI().getPath()));
                case ALL -> Utilities.extractAllConfigValues((epcConfigFIle.getURI().getPath()));
                default -> throw new IllegalArgumentException("Invalid configEnum: " + configEnum);
            };

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getMbmsConfig(ConfigEnum configEnum) {

        Map<String, String> config;

        try {

            config = switch (configEnum) {
                case COMMENTED -> Utilities.extractCommentedConfigValues((mbmsConfigFIle.getURI().getPath()));
                case UNCOMMENTED -> Utilities.extractUncommentedConfigValues((mbmsConfigFIle.getURI().getPath()));
                case ALL -> Utilities.extractAllConfigValues((mbmsConfigFIle.getURI().getPath()));
                default -> throw new IllegalArgumentException("Invalid configEnum: " + configEnum);
            };

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getRbConfig(ConfigEnum configEnum) {

        Map<String, String> config;

        try {

            config = switch (configEnum) {
                case COMMENTED -> Utilities.extractCommentedConfigValues((rbConfigFIle.getURI().getPath()));
                case UNCOMMENTED -> Utilities.extractUncommentedConfigValues((rbConfigFIle.getURI().getPath()));
                case ALL -> Utilities.extractAllConfigValues((rbConfigFIle.getURI().getPath()));
                default -> throw new IllegalArgumentException("Invalid configEnum: " + configEnum);
            };

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getRrConfig(ConfigEnum configEnum) {

        Map<String, String> config;

        try {

            config = switch (configEnum) {
                case COMMENTED -> Utilities.extractCommentedConfigValues((rrConfigFIle.getURI().getPath()));
                case UNCOMMENTED -> Utilities.extractUncommentedConfigValues((rrConfigFIle.getURI().getPath()));
                case ALL -> Utilities.extractAllConfigValues((rrConfigFIle.getURI().getPath()));
                default -> throw new IllegalArgumentException("Invalid configEnum: " + configEnum);
            };

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getSibConfig(ConfigEnum configEnum) {

        Map<String, String> config;

        try {

            config = switch (configEnum) {
                case COMMENTED -> Utilities.extractCommentedConfigValues((sibConfigFIle.getURI().getPath()));
                case UNCOMMENTED -> Utilities.extractUncommentedConfigValues((sibConfigFIle.getURI().getPath()));
                case ALL -> Utilities.extractAllConfigValues((sibConfigFIle.getURI().getPath()));
                default -> throw new IllegalArgumentException("Invalid configEnum: " + configEnum);
            };

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }

    public Map<String, String> getUeConfig(ConfigEnum configEnum) {

        Map<String, String> config;

        try {

            config = switch (configEnum) {
                case COMMENTED -> Utilities.extractCommentedConfigValues((ueConfigFIle.getURI().getPath()));
                case UNCOMMENTED -> Utilities.extractUncommentedConfigValues((ueConfigFIle.getURI().getPath()));
                case ALL -> Utilities.extractAllConfigValues((ueConfigFIle.getURI().getPath()));
                default -> throw new IllegalArgumentException("Invalid configEnum: " + configEnum);
            };

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return config;

    }


    public Map<String, String> updateEpcConfig(Map<String, String> config) {

            try {
                Utilities.updateConfigValues( epcConfigFIle.getURI().getPath(), config);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }

            return config;
    }
}
