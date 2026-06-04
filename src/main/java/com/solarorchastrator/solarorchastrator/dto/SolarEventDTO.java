package com.solarorchastrator.solarorchastrator.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class SolarEventDTO {

    @JsonProperty("event_type")
    private String eventType;

    @JsonProperty("k_index")
    private BigDecimal kIndex;

    @JsonProperty("radiation_factor")
    private Double radiationFactor;

    @JsonProperty("satellites_at_risk")
    private Integer satellitesAtRisk;

    @JsonProperty("predicted_temp_rise_celsius")
    private Double predictedTempRiseCelsius;

    @JsonProperty("solar_generation_predicted_mw")
    private Double solarGenerationPredictedMw;

    @JsonProperty("battery_charge_recommendation")
    private Boolean batteryChargeRecommendation;

    @JsonProperty("action_required")
    private Boolean actionRequired;

    private String recommendation;

    @JsonProperty("estimated_savings_brl")
    private BigDecimal estimatedSavingsBrl;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime timestamp;
}