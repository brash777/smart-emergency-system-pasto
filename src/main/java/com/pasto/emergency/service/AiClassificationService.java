package com.pasto.emergency.service;

import com.pasto.emergency.model.dto.AiResultDTO;
import com.pasto.emergency.model.enums.EmergencyType;
import com.pasto.emergency.model.enums.ServiceType;
import org.springframework.stereotype.Service;

@Service
public class AiClassificationService {

    /**
     * Por ahora simula la IA. Más adelante conectamos con Python.
     * Analiza el nombre del archivo para clasificar (simulación).
     */
    public AiResultDTO classify(String filename) {
        AiResultDTO result = new AiResultDTO();

        String name = filename.toLowerCase();
        if (name.contains("fire") || name.contains("incendio")) {
            result.setLabel("fire");
            result.setConfidence(0.92f);
        } else if (name.contains("accident") || name.contains("accidente")) {
            result.setLabel("accident");
            result.setConfidence(0.88f);
        } else if (name.contains("robo") || name.contains("robbery")) {
            result.setLabel("robbery");
            result.setConfidence(0.85f);
        } else if (name.contains("medical") || name.contains("medica")) {
            result.setLabel("medical");
            result.setConfidence(0.90f);
        } else {
            result.setLabel("other");
            result.setConfidence(0.60f);
        }
        return result;
    }

    public EmergencyType mapToEmergencyType(String label) {
        return switch (label.toLowerCase()) {
            case "fire", "incendio" -> EmergencyType.FIRE;
            case "accident", "accidente" -> EmergencyType.ACCIDENT;
            case "robbery", "robo" -> EmergencyType.ROBBERY;
            case "medical" -> EmergencyType.MEDICAL;
            default -> EmergencyType.OTHER;
        };
    }

    public ServiceType determineService(EmergencyType type) {
        return switch (type) {
            case FIRE -> ServiceType.FIRE_DEPARTMENT;
            case ACCIDENT -> ServiceType.MULTIPLE;
            case ROBBERY -> ServiceType.POLICE;
            case MEDICAL -> ServiceType.AMBULANCE;
            default -> ServiceType.POLICE;
        };
    }
}