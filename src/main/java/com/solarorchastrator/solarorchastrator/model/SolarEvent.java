package com.solarorchastrator.solarorchastrator.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "solar_events")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SolarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solar_events_seq")
    @SequenceGenerator(name = "solar_events_seq", sequenceName = "sq_solar_events", allocationSize = 1)
    private Long id;

    @Column(name = "event_type", length = 50)
    private String event_type;

    @Column(name = "k_index")
    private BigDecimal kIndex;

    @Column(name = "radiation_factor")
    private BigDecimal radiationFactor;

    @Column(name = "satellites_at_risk")
    private Long satellitesAtRisk;

    @Column(name = "predicted_temp_rise_celsius")
    private BigDecimal predictedTempRiseCelsius;

    @Column(name = "solar_generation_predicted_mw")
    private BigDecimal solarGenerationPredictedMw;

    @Column(name = "battery_charge_recommendation")
    private Boolean batteryChargeRecommendation;

    @Column(name = "action_required")
    private Boolean actionRequired;

    @Column(name = "recommendation", columnDefinition = "CLOB")
    private String recommendation;

    @Column(name = "estimated_savings_brl")
    private BigDecimal estimatedSavingsBrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}