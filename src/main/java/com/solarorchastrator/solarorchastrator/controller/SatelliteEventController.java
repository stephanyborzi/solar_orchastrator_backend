package com.solarorchastrator.solarorchastrator.controller;

import com.solarorchastrator.solarorchastrator.dto.SpaceStatusResponseDTO;
import com.solarorchastrator.solarorchastrator.service.SatelliteEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class SatelliteEventController {

    private final SatelliteEventService satelliteEventService;

    public SatelliteEventController(SatelliteEventService satelliteEventService) {
        this.satelliteEventService = satelliteEventService;
    }

    @GetMapping("/satellites")
    public ResponseEntity<SpaceStatusResponseDTO> getAllSatellites(@RequestParam(value = "risk", required = false) String risk) {
        try {
            SpaceStatusResponseDTO spaceStatus = satelliteEventService.getSatellites(risk);

            if (spaceStatus.getSatellites() == null || (spaceStatus.getSatellites().isEmpty() && spaceStatus.getSummary().getMigratedCount() == 0)) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(spaceStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}