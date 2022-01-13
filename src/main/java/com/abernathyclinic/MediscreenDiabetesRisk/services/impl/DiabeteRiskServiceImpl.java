package com.abernathyclinic.MediscreenDiabetesRisk.services.impl;

import com.abernathyclinic.MediscreenDiabetesRisk.data.ListTriggerTerms;
import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.models.Patient;
import com.abernathyclinic.MediscreenDiabetesRisk.models.PatientNote;
import com.abernathyclinic.MediscreenDiabetesRisk.proxy.NotesHistoryProxy;
import com.abernathyclinic.MediscreenDiabetesRisk.proxy.PatientInfosProxy;
import com.abernathyclinic.MediscreenDiabetesRisk.services.DiabeteRiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DiabeteRiskServiceImpl implements DiabeteRiskService {

    private final Logger logger = LoggerFactory.getLogger(DiabeteRiskServiceImpl.class);
    private final PatientInfosProxy patientInfosProxy;
    private final NotesHistoryProxy notesHistoryProxy;

    public DiabeteRiskServiceImpl(PatientInfosProxy patientInfosProxy, NotesHistoryProxy notesHistoryProxy) {
        this.patientInfosProxy = patientInfosProxy;
        this.notesHistoryProxy = notesHistoryProxy;
    }

    @Override
    public DiabeteRisk generateRiskById(Integer patientId) {

        Patient patient = patientInfosProxy.readPatientById(patientId);

        if (patient == null) {
            logger.error("Patient Id introuvable, patient inconnu.");
            return null;
        }

        List<PatientNote> patientNoteList = notesHistoryProxy.readNotesHistoryByPatientId(patient.getId());

        DiabeteRisk result = calculateRisk(patient.getDateOfBirth(), patient.getGender(), patientNoteList);

        result.setPatientId(patient.getId());

        return result;
    }

    @Override
    public DiabeteRisk generateRiskByFirstNameAndLastName(String firstName, String lastName) {

        Patient patient = patientInfosProxy.readPatientByFirstNameAndLastName(firstName, lastName);

        if (patient == null) {
            logger.error("Patient introuvable, patient inconnu.");
            return null;
        }

        List<PatientNote> patientNoteList = notesHistoryProxy.readNotesHistoryByPatientId(patient.getId());

        DiabeteRisk result = calculateRisk(patient.getDateOfBirth(), patient.getGender(), patientNoteList);

        result.setPatientId(patient.getId());

        return result;
    }

    @Override
    public DiabeteRisk calculateRisk(LocalDate patientDateOfBirth, String patientGender, List<PatientNote> patientNoteList) {

        DiabeteRisk diabeteRisk = new DiabeteRisk();

        diabeteRisk.setPatientDateOfBirth(patientDateOfBirth);

        diabeteRisk.setPatientGender(patientGender);

        for (PatientNote patientNote : patientNoteList) {

            String practitionerNote = patientNote.getPractitionerNote();

            for (String triggerTerm : ListTriggerTerms.listTriggerTerms) {

                if (practitionerNote.contains(triggerTerm)) {

                    diabeteRisk.getTriggerTerms().add(triggerTerm);

                }
            }
        }

        int patientAge = LocalDate.now().getYear() - diabeteRisk.getPatientDateOfBirth().getYear();

        logger.debug("Le patient à " + patientAge);

        // Si le patient ne contient aucun des des termes déclencheurs.
        if (diabeteRisk.getTriggerTerms().isEmpty()) {
            diabeteRisk.setRiskLevel(0);
            logger.debug("Aucun risque");
        }

        // Si le patient a plus de 30 ans.
        if (patientAge > 30) {

            logger.debug("Plus de 30 ans.");
            // Si le patient contient entre 2 et 5 termes déclencheurs.
            if (diabeteRisk.getTriggerTerms().size() >= 2 && diabeteRisk.getTriggerTerms().size() < 6) {
                diabeteRisk.setRiskLevel(1);
                logger.debug("Entre 2 et 5 termes.");
            }

            // Si le patient contient entre 6 et 7 termes déclencheurs.
            if (diabeteRisk.getTriggerTerms().size() >= 6 && diabeteRisk.getTriggerTerms().size() < 8) {
                diabeteRisk.setRiskLevel(2);
                logger.debug("Entre 6 et 7 termes.");
            }

            // Si le patient contient 8 ou plus termes déclencheurs.
            if (diabeteRisk.getTriggerTerms().size() >= 8) {
                diabeteRisk.setRiskLevel(3);
                logger.debug("Entre 8 et plus termes.");
            }
        }

        // Si le patient a moins de/ou 30 ans.
        if (patientAge <= 30) {

            logger.debug("Moins ou égale à 30 ans.");
            // Si le patient est un homme.
            if (Objects.equals(diabeteRisk.getPatientGender(), "M")) {

                logger.debug("Homme");

                // Si le patient contient moins de 3 termes déclencheurs.
                if (diabeteRisk.getTriggerTerms().size() < 3) {
                    diabeteRisk.setRiskLevel(0);
                    logger.debug("Moins de 3 termes.");
                }

                // Si le patient contient entre 3 et 4 termes déclencheurs.
                if (diabeteRisk.getTriggerTerms().size() >= 3 && diabeteRisk.getTriggerTerms().size() < 5) {
                    diabeteRisk.setRiskLevel(2);
                    logger.debug("Entre 3 et 4 termes.");

                }

                // Si le patient contient 5 ou plus termes déclencheurs.
                if (diabeteRisk.getTriggerTerms().size() >= 5) {
                    diabeteRisk.setRiskLevel(3);
                    logger.debug("Entre 5 et plus termes.");
                }

            }

            // Si le patient est une femme.
            if (Objects.equals(diabeteRisk.getPatientGender(), "F")) {

                logger.debug("Femme");

                // Si la patiente contient moins de 4 termes déclencheurs.
                if (diabeteRisk.getTriggerTerms().size() < 4) {
                    diabeteRisk.setRiskLevel(0);
                    logger.debug("Moins de 4 termes.");
                }

                // Si la patiente contient entre 4 et 6 termes déclencheurs.
                if (diabeteRisk.getTriggerTerms().size() >= 4 && diabeteRisk.getTriggerTerms().size() < 7) {
                    diabeteRisk.setRiskLevel(2);
                    logger.debug("Entre 4 et 6 termes.");
                }

                // Si la patiente contient 7 ou plus termes déclencheurs.
                if (diabeteRisk.getTriggerTerms().size() >= 7) {
                    diabeteRisk.setRiskLevel(3);
                    logger.debug("Entre 7 et plus termes.");
                }

            }
        }

        logger.debug(String.valueOf(diabeteRisk));

        return diabeteRisk;
    }
}
