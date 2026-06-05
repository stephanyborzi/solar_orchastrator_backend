package com.solarorchastrator.solarorchastrator.service;

import com.solarorchastrator.solarorchastrator.dto.SatelliteEventDTO;
import com.solarorchastrator.solarorchastrator.dto.SpaceStatusResponseDTO;
import com.solarorchastrator.solarorchastrator.dto.SpaceSummaryDTO;
import com.solarorchastrator.solarorchastrator.model.SatelliteEvent;
import com.solarorchastrator.solarorchastrator.repository.SatelliteEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SatelliteEventService {

    private final SatelliteEventRepository satelliteEventRepository;

    public SatelliteEventService(SatelliteEventRepository satelliteEventRepository) {
        this.satelliteEventRepository = satelliteEventRepository;
    }

    @Transactional
    public SatelliteEventDTO convertToDTO(SatelliteEvent entity) {
        SatelliteEventDTO dto = new SatelliteEventDTO();

        dto.setName(entity.getName());
        dto.setNoradId(BigDecimal.valueOf(entity.getNoradId()));
        dto.setAltitudeKm(entity.getAltitudeKm());
        dto.setInclinationDeg(entity.getInclinationDeg());
        dto.setProcessingLoadPct(entity.getProcessingLoadPct());
        dto.setTemperatureCelsius(entity.getTemperatureCelsius());
        dto.setRiskLevel(entity.getRiskLevel());

        dto.setMigratedTo(entity.getMigratedToValue());

        return dto;
    }

    public SpaceStatusResponseDTO getSatellites(String riskLevel) {
        List<SatelliteEvent> filteredEntities;
        if (riskLevel != null && !riskLevel.isBlank()) {
            filteredEntities = satelliteEventRepository.findByRiskLevelIgnoreCase(riskLevel);
        } else {
            filteredEntities = satelliteEventRepository.findAll();
        }

        List<SatelliteEvent> allEntities = satelliteEventRepository.findAll();

        long migratedCount = 0;
        double totalProcessingLoadMigrated = 0.0;

        for (SatelliteEvent sat : allEntities) {
            if (sat.getMigrations() != null && !sat.getMigrations().isEmpty()) {
                migratedCount++;
                if (sat.getProcessingLoadPct() != null) {
                    totalProcessingLoadMigrated += sat.getProcessingLoadPct().doubleValue();
                }
            }
        }

        double expectedLoss = 0.0;
        if (!allEntities.isEmpty() && migratedCount > 0) {
            expectedLoss = (totalProcessingLoadMigrated / allEntities.size()) * 0.1;
            expectedLoss = Math.round(expectedLoss * 100.0) / 100.0;
        }

        List<SatelliteEventDTO> dtos = filteredEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        SpaceSummaryDTO summary = new SpaceSummaryDTO();
        summary.setMigratedCount(migratedCount);
        summary.setExpectedDataLossPct(expectedLoss);

        SpaceStatusResponseDTO response = new SpaceStatusResponseDTO();
        response.setSatellites(dtos);
        response.setSummary(summary);

        return response;
    }
}