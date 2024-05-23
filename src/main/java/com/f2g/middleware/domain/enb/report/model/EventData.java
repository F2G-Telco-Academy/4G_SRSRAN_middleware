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
    @JsonProperty("rnti")
    private Integer rnti;
    @JsonProperty("asn1_length")
    private Integer asn1Length;
    @JsonProperty("asn1_message")
    private String asn1Message;
    @JsonProperty("asn1_type")
    private Integer asn1Type;
    @JsonProperty("additional")
    private Integer additional;

}
