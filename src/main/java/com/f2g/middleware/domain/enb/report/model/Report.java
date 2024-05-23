package com.f2g.middleware.domain.enb.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Report {
    @JsonProperty("type")
    private String type;
    @JsonProperty("timestamp")
    private double timestamp;
    @JsonProperty("carrier_id")
    private Integer carrierId;
    @JsonProperty("cell_id")
    private Integer cellId;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("event_data")
    private EventData eventData;
    @JsonProperty("cell_list")
    private List<CellList> cellList;

    // method to get timestamp as a Timestamp object
    public Timestamp getTimestampAsTimestamp() {
        return new Timestamp((long) (timestamp * 1000));
    }



    public static class CellList {
        @JsonProperty("cell_container")
        private CellContainer cellContainer;

    }
}
