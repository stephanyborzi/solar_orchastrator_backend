package com.solarorchastrator.solarorchastrator.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class SpaceStatusResponseDTO {

    @JsonProperty("satellites")
    private List<SatelliteEventDTO> satellites;

    @JsonProperty("summary")
    private SpaceSummaryDTO summary;
}