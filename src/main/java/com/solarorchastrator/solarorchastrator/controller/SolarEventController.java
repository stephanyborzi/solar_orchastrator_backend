package com.solarorchastrator.solarorchastrator.controller;

import com.solarorchastrator.solarorchastrator.dto.SolarEventDTO;
import com.solarorchastrator.solarorchastrator.model.SolarEvent;
import com.solarorchastrator.solarorchastrator.service.SolarEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SolarEventController {

    public final SolarEventService solarEventService;

    public SolarEventController(SolarEventService solarEventService) {
        this.solarEventService = solarEventService;
    }

    @PostMapping("/solar-events")
    public ResponseEntity<SolarEvent> receiveSolarEvent(@RequestBody SolarEventDTO solarEventDTO) {
        SolarEvent savedEvent = solarEventService.createSolarEvent(solarEventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

//    @GetMapping("/dashboard")
//    public ResponseEntity<SolarEvent> getDashboard() {
//        try {
//            SolarEvent latestEvent = solarEventService.getLatestDashboardData();
//            return ResponseEntity.ok(latestEvent);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

}
