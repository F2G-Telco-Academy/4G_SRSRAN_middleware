package com.f2g.middleware.domain.enb.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
public class UserEquipment {
    @JsonProperty("ue_rnti")
    private Integer ueRnti;
}
