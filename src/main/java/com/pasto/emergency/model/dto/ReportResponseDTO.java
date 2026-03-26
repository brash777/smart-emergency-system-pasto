package com.pasto.emergency.model.dto;

import com.pasto.emergency.model.enums.EmergencyType;
import com.pasto.emergency.model.enums.ServiceType;
import java.time.LocalDateTime;

public class ReportResponseDTO {

    private Long id;
    private EmergencyType classification;
    private Float confidence;
    private ServiceType assignedService;
    private String status;
    private String message;
    private LocalDateTime createdAt;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EmergencyType getClassification() { return classification; }
    public void setClassification(EmergencyType classification) { this.classification = classification; }
    public Float getConfidence() { return confidence; }
    public void setConfidence(Float confidence) { this.confidence = confidence; }
    public ServiceType getAssignedService() { return assignedService; }
    public void setAssignedService(ServiceType assignedService) { this.assignedService = assignedService; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}