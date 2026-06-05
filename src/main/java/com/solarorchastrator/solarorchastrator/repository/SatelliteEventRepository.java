package com.solarorchastrator.solarorchastrator.repository;

import com.solarorchastrator.solarorchastrator.model.SatelliteEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SatelliteEventRepository extends JpaRepository<SatelliteEvent, Long> {
    List<SatelliteEvent> findByRiskLevelIgnoreCase(String riskLevel);
}
