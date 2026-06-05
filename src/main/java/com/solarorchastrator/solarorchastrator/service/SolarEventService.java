package com.solarorchastrator.solarorchastrator.service;
import com.solarorchastrator.solarorchastrator.dto.SolarEventDTO;
import com.solarorchastrator.solarorchastrator.model.SolarEvent;
import com.solarorchastrator.solarorchastrator.repository.SolarEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolarEventService {

    public final SolarEventRepository solarEventRepository;
    public SolarEventService(SolarEventRepository solarEventRepository) {
        this.solarEventRepository = solarEventRepository;
    }

    @Transactional
    public SolarEvent processAndSaveEvent(SolarEventDTO dto) {
        SolarEvent solarEvent = new SolarEvent();

        solarEvent.setKIndex(dto.getKIndex());
        solarEvent.setRadiationFactor(BigDecimal.valueOf(dto.getRadiationFactor()));
        solarEvent.setSatellitesAtRisk(Long.valueOf(dto.getSatellitesAtRisk()));
        solarEvent.setPredictedTempRiseCelsius(BigDecimal.valueOf(dto.getPredictedTempRiseCelsius()));
        solarEvent.setSolarGenerationPredictedMw(BigDecimal.valueOf(dto.getSolarGenerationPredictedMw()));
        solarEvent.setCreatedAt(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());

        if (solarEvent.getKIndex().compareTo(BigDecimal.valueOf(6)) >= 0) {
            solarEvent.setActionRequired(true);
            solarEvent.setEvent_type("SOLAR_STORM");
        } else {
            solarEvent.setActionRequired(false);
            solarEvent.setEvent_type("NORMAL");
        }

        if (solarEvent.getPredictedTempRiseCelsius().compareTo(BigDecimal.valueOf(20)) > 0) {
            solarEvent.setBatteryChargeRecommendation(true);
        } else {
            solarEvent.setBatteryChargeRecommendation(false);
        }

        if (dto.getRecommendation() == null || dto.getRecommendation().isBlank()) {
            if (solarEvent.getActionRequired()) {
                solarEvent.setRecommendation("Migre " + solarEvent.getSatellitesAtRisk() + " satélites e carregue baterias para 100%.");
            } else {
                solarEvent.setRecommendation("Sistema em condições normais. Nenhuma ação necessária.");
            }
        } else {
            solarEvent.setRecommendation(dto.getRecommendation());
        }

        if (dto.getEstimatedSavingsBrl() == null) {
            BigDecimal calculatedSavings = solarEvent.getSolarGenerationPredictedMw().multiply(BigDecimal.valueOf(10000.0));
            solarEvent.setEstimatedSavingsBrl(calculatedSavings);
        } else {
            solarEvent.setEstimatedSavingsBrl(dto.getEstimatedSavingsBrl());
        }

        return solarEventRepository.save(solarEvent);
    }

    public SolarEvent getLatestDashboardData() {
        return solarEventRepository.findFirstByOrderByIdDesc();
    }
}