package com.pasto.emergency.model.dto;

import java.util.Map;

public class AiResultDTO {

    private String label;
    private Float confidence;
    private Map<String, Float> allPredictions;

    // Getters y Setters
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Float getConfidence() { return confidence; }
    public void setConfidence(Float confidence) { this.confidence = confidence; }
    public Map<String, Float> getAllPredictions() { return allPredictions; }
    public void setAllPredictions(Map<String, Float> allPredictions) { this.allPredictions = allPredictions; }
}