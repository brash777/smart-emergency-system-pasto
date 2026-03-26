package com.pasto.emergency.model.entity;

import com.pasto.emergency.model.enums.EmergencyType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String imageUrl;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    private String address;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private EmergencyType aiClassification;

    private Float aiConfidence;

    @Column
    private String status = "PENDING";

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    private Emergency emergency;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public EmergencyType getAiClassification() { return aiClassification; }
    public void setAiClassification(EmergencyType aiClassification) { this.aiClassification = aiClassification; }
    public Float getAiConfidence() { return aiConfidence; }
    public void setAiConfidence(Float aiConfidence) { this.aiConfidence = aiConfidence; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Emergency getEmergency() { return emergency; }
    public void setEmergency(Emergency emergency) { this.emergency = emergency; }
}