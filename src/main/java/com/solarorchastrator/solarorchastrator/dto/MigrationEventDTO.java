package com.solarorchastrator.solarorchastrator.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class MigrationEventDTO {

    @JsonProperty("satellite_origin_id")
    private Long satelliteOriginId;

    @JsonProperty("datacenter_destination_id")
    private Long datacenterDestinationId;

    @JsonProperty("solar_event_id")
    private Long solarEventId;

    @JsonProperty("migrated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime migratedAt;
}