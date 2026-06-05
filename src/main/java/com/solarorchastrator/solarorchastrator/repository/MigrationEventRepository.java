package com.solarorchastrator.solarorchastrator.repository;

import com.solarorchastrator.solarorchastrator.model.MigrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MigrationEventRepository extends JpaRepository<MigrationEvent, Long> {
    MigrationEvent findFirstByOrderByIdDesc();
}
