package com.solarorchastrator.solarorchastrator.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class SatelliteEventDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("norad_id")
    private BigDecimal noradId;

    @JsonProperty("altitude_km")
    private BigDecimal altitudeKm;

    @JsonProperty("inclination_deg")
    private BigDecimal inclinationDeg;

    @JsonProperty("processing_load_pct")
    private BigDecimal processingLoadPct;

    @JsonProperty("temperature_celsius")
    private BigDecimal temperatureCelsius;

    @JsonProperty("risk_level")
    private String riskLevel;

    @JsonProperty("solar_event_id")
    private BigDecimal solarEventId;

    @JsonProperty("migrated_to")
    private String migratedTo;

}
