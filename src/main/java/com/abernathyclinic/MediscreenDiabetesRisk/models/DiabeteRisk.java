package com.abernathyclinic.MediscreenDiabetesRisk.models;

import java.util.List;

public class DiabeteRisk {

    private Integer patientId;

    private Integer patientDateOfBirth;

    private String patientGender;

    private Integer riskLevel;

    private List<String> triggerTerms;

    public DiabeteRisk(Integer patientId, Integer patientDateOfBirth, String patientGender, Integer riskLevel, List<String> triggerTerms) {
        this.patientId = patientId;
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientGender = patientGender;
        this.riskLevel = riskLevel;
        this.triggerTerms = triggerTerms;
    }

    public DiabeteRisk() {
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(Integer patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public List<String> getTriggerTerms() {
        return triggerTerms;
    }

    public void setTriggerTerms(List<String> triggerTerms) {
        this.triggerTerms = triggerTerms;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }
}
