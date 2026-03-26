package com.pasto.emergency.model.entity;

import com.pasto.emergency.model.enums.EmergencyStatus;
import com.pasto.emergency.model.enums.EmergencyType;
import com.pasto.emergency.model.enums.ServiceType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergencies")
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Enumerated(EnumType.STRING)
    private EmergencyType type;

    @Enumerated(EnumType.STRING)
    private ServiceType assignedService;

    @Enumerated(EnumType.STRING)
    private EmergencyStatus status = EmergencyStatus.CREATED;

    private String assignedUnit;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime resolvedAt;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Report getReport() { return report; }
    public void setReport(Report report) { this.report = report; }
    public EmergencyType getType() { return type; }
    public void setType(EmergencyType type) { this.type = type; }
    public ServiceType getAssignedService() { return assignedService; }
    public void setAssignedService(ServiceType assignedService) { this.assignedService = assignedService; }
    public EmergencyStatus getStatus() { return status; }
    public void setStatus(EmergencyStatus status) { this.status = status; }
    public String getAssignedUnit() { return assignedUnit; }
    public void setAssignedUnit(String assignedUnit) { this.assignedUnit = assignedUnit; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
}