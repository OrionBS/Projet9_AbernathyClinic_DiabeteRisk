package com.abernathyclinic.MediscreenDiabetesRisk.services;

import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.models.PatientNote;

import java.time.LocalDate;
import java.util.List;

public interface DiabeteRiskService {

    /**
     * Permet de générer le risque de diabète en fonction de l'id du patient.
     * @param patientId identifiant unique du patient.
     * @return génére un diabète risque.
     */
    DiabeteRisk generateRiskById(Integer patientId);

    /**
     * Permet de générer le risque de diabète en fonction du prénom et nom du patient.
     * @param firstName prénom du patient.
     * @param lastName nom du patient.
     * @return génére un diabète risque.
     */
    DiabeteRisk generateRiskByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Calcul le risque d'un patient en fonction de son age, son genre et sa liste de notes.
     * @param patientDateOfBirth date de naissance du patient.
     * @param patientGender genre du patient.
     * @param patientNoteList liste de note du patient.
     * @return le risque de diabete du patien.
     */
    DiabeteRisk calculateRisk(LocalDate patientDateOfBirth, String patientGender, List<PatientNote> patientNoteList);
}
