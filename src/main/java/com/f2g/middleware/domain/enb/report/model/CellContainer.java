package com.f2g.middleware.domain.enb.report.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
public class CellContainer {
    private Integer carriedId;
    private Integer pci;
    private Integer nofRach;
    private Object[] ueList;
}
