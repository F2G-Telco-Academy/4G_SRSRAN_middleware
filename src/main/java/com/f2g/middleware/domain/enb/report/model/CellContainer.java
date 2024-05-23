package com.f2g.middleware.domain.enb.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Getter
@Setter
@FieldNameConstants
public  class CellContainer {
    @JsonProperty("carrier_id")
    private Integer carrierId;
    @JsonProperty("pci")
    private Integer pci;
    @JsonProperty("nof_rach")
    private Integer nofRach;
    @JsonProperty("ue_list")
    private List<UEList> ueList;

    public static class UEList {
        @JsonProperty("ue_container")
        private UEContainer ueContainer;

    }
}
