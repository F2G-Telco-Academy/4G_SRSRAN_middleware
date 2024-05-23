package com.f2g.middleware.domain.enb.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Getter
@Setter
@FieldNameConstants
public class UEContainer {
    @JsonProperty("ue_rnti")
    private Integer ueRnti;
    @JsonProperty("dl_cqi")
    private Integer dlCqi;
    @JsonProperty("dl_mcs")
    private Integer dlMcs;
    @JsonProperty("ul_pusch_rssi")
    private Integer ulPuschRssi;
    @JsonProperty("ul_pucch_rssi")
    private Integer ulPucchRssi;
    @JsonProperty("ul_pucch_ni")
    private Integer ulPucchNi;
    @JsonProperty("ul_pusch_tpc")
    private Integer ulPuschTpc;
    @JsonProperty("ul_pucch_tpc")
    private Integer ulPucchTpc;

    @JsonProperty("dl_cqi_offset")
    private Integer dlCqiOffset;
    @JsonProperty("ul_snr_offset")
    private Integer ulSnrOffset;
    @JsonProperty("dl_bitrate")
    private Integer dlBitrate;
    @JsonProperty("dl_bler")
    private Integer dlBler;
    @JsonProperty("ul_snr")
    private Integer ulSnr;
    @JsonProperty("ul_mcs")
    private Integer ulMcs;
    @JsonProperty("ul_bitrate")
    private Integer ulBitrate;
    @JsonProperty("ul_bler")
    private Integer ulBler;
    @JsonProperty("ul_phr")
    private Integer ulPhr;
    @JsonProperty("ul_bsr")
    private Integer ulBsr;
    @JsonProperty("bearer_list")
    private List<Object> bearerList;



}
