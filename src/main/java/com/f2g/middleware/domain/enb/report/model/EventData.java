package com.f2g.middleware.domain.enb.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EventData {
    @JsonProperty("pci")
    private Integer pci;
    @JsonProperty("cell_identity")
    private String cellIdentity;
    @JsonProperty("sib9_home_enb_name")
    private String sib9HomeEnbName;
}
