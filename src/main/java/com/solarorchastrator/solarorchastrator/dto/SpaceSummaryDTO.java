package com.solarorchastrator.solarorchastrator.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class SpaceSummaryDTO {

    @JsonProperty("migrated_count")
    private long migratedCount;

    @JsonProperty("expected_data_loss_pct")
    private double expectedDataLossPct;
}
