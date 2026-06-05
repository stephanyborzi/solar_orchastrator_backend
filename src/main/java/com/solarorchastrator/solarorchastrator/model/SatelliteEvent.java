package com.solarorchastrator.solarorchastrator.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "satellites")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SatelliteEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "satellites_seq")
    @SequenceGenerator(name = "satellites_seq", sequenceName = "sq_satellites", allocationSize = 1)
    @JsonIgnore
    private Long id;

    private String name;

    private Long noradId;

    private BigDecimal altitudeKm;

    private BigDecimal inclinationDeg;

    private BigDecimal processingLoadPct;

    private BigDecimal temperatureCelsius;

    private String riskLevel;

    @Column(name = "solar_event_id")
    @JsonIgnore
    private Long solarEventId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "satellite_origin_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private List<MigrationEvent> migrations;

    @JsonProperty("migrated_to")
    public String getMigratedToValue() {
        if (migrations != null && !migrations.isEmpty()) {
            Long destinationId = migrations.get(migrations.size() - 1).getDatacenterDestinationId();
            return "Datacenter " + destinationId;
        }
        return null;
    }
}