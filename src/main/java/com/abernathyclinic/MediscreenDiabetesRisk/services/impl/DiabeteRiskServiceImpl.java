package com.abernathyclinic.MediscreenDiabetesRisk.services.impl;

import com.abernathyclinic.MediscreenDiabetesRisk.data.ListTriggerTerms;
import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.models.Patient;
import com.abernathyclinic.MediscreenDiabetesRisk.models.PatientNote;
import com.abernathyclinic.MediscreenDiabetesRisk.proxy.NotesHistoryProxy;
import com.abernathyclinic.MediscreenDiabetesRisk.proxy.PatientInfosProxy;
import com.abernathyclinic.MediscreenDiabetesRisk.services.DiabeteRiskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiabeteRiskServiceImpl implements DiabeteRiskService {

    private final PatientInfosProxy patientInfosProxy;
    private final NotesHistoryProxy notesHistoryProxy;

    public DiabeteRiskServiceImpl(PatientInfosProxy patientInfosProxy, NotesHistoryProxy notesHistoryProxy) {
        this.patientInfosProxy = patientInfosProxy;
        this.notesHistoryProxy = notesHistoryProxy;
    }

    @Override
    public DiabeteRisk generateRiskById(Integer patientId) {

        Patient patient = patientInfosProxy.readPatientById(patientId);

        return null;
    }

    @Override
    public DiabeteRisk generateRiskByFirstNameAndLastName(String firstName, String lastName) {

        Patient patient = patientInfosProxy.readPatientByFirstNameAndLastName(firstName,lastName);

        List<PatientNote> patientNoteList = notesHistoryProxy.readNotesHistoryByPatientId(patient.getId());

        Integer result = calculateRisk(patient.getDateOfBirth(), patient.getGender(), patientNoteList);

        return null;
    }

    @Override
    public Integer calculateRisk(LocalDate patientDateOfBirth, String patientGender, List<PatientNote> patientNoteList) {

        DiabeteRisk diabeteRisk = new DiabeteRisk();

        for (PatientNote patientNote : patientNoteList) {

            String practitionerNote = patientNote.getPractitionerNote();

            for (String triggerTerm : ListTriggerTerms.listTriggerTerms) {

                if (practitionerNote.contains(triggerTerm)) {

                    diabeteRisk.getTriggerTerms().add(triggerTerm);

                }
            }
        }

        diabeteRisk.setPatientDateOfBirth(patientDateOfBirth.getYear());

        return null;
    }
}
