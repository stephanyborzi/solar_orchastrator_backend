package com.solarorchastrator.solarorchastrator.service;

import com.solarorchastrator.solarorchastrator.dto.MigrationEventDTO;
import com.solarorchastrator.solarorchastrator.model.MigrationEvent;
import com.solarorchastrator.solarorchastrator.repository.MigrationEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MigrationEventService {
    public final MigrationEventRepository migrationEventRepository;
    public MigrationEventService(MigrationEventRepository migrationEventRepository) {
        this.migrationEventRepository = migrationEventRepository;
    }

    @Transactional
    public MigrationEvent createMigrationEvent(MigrationEventDTO dto) {
        MigrationEvent migrationEvent = new MigrationEvent();

        migrationEvent.setSatelliteOriginId(dto.getSatelliteOriginId());
        migrationEvent.setDatacenterDestinationId(dto.getDatacenterDestinationId());
        migrationEvent.setSolarEventId(dto.getSolarEventId());
        migrationEvent.setMigratedAt(dto.getMigratedAt());

        return migrationEventRepository.save(migrationEvent);
    }

}
