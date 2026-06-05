package com.solarorchastrator.solarorchastrator.controller;

import com.solarorchastrator.solarorchastrator.dto.MigrationEventDTO;
import com.solarorchastrator.solarorchastrator.model.MigrationEvent;
import com.solarorchastrator.solarorchastrator.service.MigrationEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MigrationEventController {
    public MigrationEventService migrationEventService;
    public MigrationEventController(MigrationEventService migrationEventService) {
        this.migrationEventService = migrationEventService;
    }
    @PostMapping("/migration")
    public ResponseEntity<MigrationEvent> receiveMigration(@RequestBody MigrationEventDTO migrationEventDTO) {
        MigrationEvent savedEvent = migrationEventService.createMigrationEvent(migrationEventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }
}
