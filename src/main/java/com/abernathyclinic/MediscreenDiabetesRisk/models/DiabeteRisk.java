package com.abernathyclinic.MediscreenDiabetesRisk.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiabeteRisk {

    private Integer patientId;

    private LocalDate patientDateOfBirth;

    private String patientGender;

    private Integer riskLevel;

    private List<String> triggerTerms = new ArrayList<>();

    public DiabeteRisk(Integer patientId, LocalDate patientDateOfBirth, String patientGender, Integer riskLevel, List<String> triggerTerms) {
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

    public LocalDate getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
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

    @Override
    public String toString() {
        return "DiabeteRisk{" +
                "patientId=" + patientId +
                ", patientDateOfBirth=" + patientDateOfBirth +
                ", patientGender='" + patientGender + '\'' +
                ", riskLevel=" + riskLevel +
                ", triggerTerms=" + triggerTerms +
                '}';
    }
}
