package com.pasto.emergency.controller;

import com.pasto.emergency.model.entity.Emergency;
import com.pasto.emergency.model.enums.EmergencyStatus;
import com.pasto.emergency.repository.EmergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emergencies")
@CrossOrigin(origins = "*")
public class EmergencyController {

    @Autowired
    private EmergencyRepository emergencyRepository;

    @GetMapping
    public ResponseEntity<List<Emergency>> getAll() {
        return ResponseEntity.ok(emergencyRepository.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Emergency>> getActive() {
        return ResponseEntity.ok(
                emergencyRepository.findByStatus(EmergencyStatus.CREATED));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Emergency> updateStatus(
            @PathVariable Long id,
            @RequestParam EmergencyStatus status) {
        Emergency emergency = emergencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
        emergency.setStatus(status);
        return ResponseEntity.ok(emergencyRepository.save(emergency));
    }
}