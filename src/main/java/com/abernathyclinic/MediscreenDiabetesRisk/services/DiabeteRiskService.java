package com.abernathyclinic.MediscreenDiabetesRisk.services;

import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.models.PatientNote;

import java.time.LocalDate;
import java.util.List;

public interface DiabeteRiskService {
    DiabeteRisk generateRiskById(Integer patientId);

    DiabeteRisk generateRiskByFirstNameAndLastName(String firstName, String lastName);

    DiabeteRisk calculateRisk(LocalDate patientDateOfBirth, String patientGender, List<PatientNote> patientNoteList);
}
