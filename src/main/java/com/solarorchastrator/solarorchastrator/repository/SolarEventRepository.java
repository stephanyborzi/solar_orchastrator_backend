package com.solarorchastrator.solarorchastrator.repository;

import com.solarorchastrator.solarorchastrator.model.SolarEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarEventRepository extends JpaRepository<SolarEvent, Long> {
    SolarEvent findFirstByOrderByIdDesc();
}
