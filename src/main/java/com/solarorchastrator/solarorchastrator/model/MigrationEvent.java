package com.solarorchastrator.solarorchastrator.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "migrations")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class MigrationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "migrations_seq")
    @SequenceGenerator(name = "migrations_seq", sequenceName = "sq_migrations", allocationSize = 1)
    private Long id;

    @Column(name = "satellite_origin_id")
    private Long satelliteOriginId;

    @Column(name = "datacenter_destination_id")
    private Long datacenterDestinationId;

    @Column(name = "solar_event_id")
    private Long solarEventId;

    @Column(name = "migrated_at")
    private LocalDateTime migratedAt;
}