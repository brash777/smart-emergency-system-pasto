package com.pasto.emergency.model.dto;

public class ReportRequestDTO {

    private Double latitude;
    private Double longitude;
    private String description;

    // Getters y Setters
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}