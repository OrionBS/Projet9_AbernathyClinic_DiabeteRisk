package com.abernathyclinic.MediscreenDiabetesRisk.units;

import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.models.Patient;
import com.abernathyclinic.MediscreenDiabetesRisk.models.PatientNote;
import com.abernathyclinic.MediscreenDiabetesRisk.proxy.NotesHistoryProxy;
import com.abernathyclinic.MediscreenDiabetesRisk.proxy.PatientInfosProxy;
import com.abernathyclinic.MediscreenDiabetesRisk.services.impl.DiabeteRiskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DiabeteRiskServiceTest {

    @InjectMocks
    private DiabeteRiskServiceImpl diabeteRiskService;

    @Mock
    private PatientInfosProxy patientInfosProxy;
    @Mock
    private NotesHistoryProxy notesHistoryProxy;

    @Test
    public void testGenerateRiskById() {
        //GIVEN
        DiabeteRisk diabeteRisk = new DiabeteRisk(12, LocalDate.of(1970,12,25), "M", 1, Arrays.asList("Taille", "Vertige"));
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.of(1970,12,25), "M", "Somewhere", "Some phone");
        PatientNote patientNote = new PatientNote(25,12, LocalDate.now(),"Taille");
        PatientNote patientNote2 = new PatientNote(25,12, LocalDate.now(),"Vertige");
        List<PatientNote> patientNoteList = Arrays.asList(patientNote,patientNote2);
        Mockito.when(patientInfosProxy.readPatientById(patient.getId())).thenReturn(patient);
        Mockito.when(notesHistoryProxy.readNotesHistoryByPatientId(patient.getId())).thenReturn(patientNoteList);

        //WHEN
        DiabeteRisk result = diabeteRiskService.generateRiskById(patient.getId());

        //THEN
        Mockito.verify(patientInfosProxy,Mockito.times(1)).readPatientById(patient.getId());
        Mockito.verify(notesHistoryProxy,Mockito.times(1)).readNotesHistoryByPatientId(patient.getId());
        System.out.println(diabeteRisk);
        System.out.println(result);
        Assertions.assertEquals(diabeteRisk.toString(),result.toString());

    }

    @Test
    public void testGenerateRiskByFirstNameAndLastName() {
        //GIVEN
        String firstName = "testFirstName";
        String lastName = "testLastName";
        DiabeteRisk diabeteRisk = new DiabeteRisk(12, LocalDate.of(2000,12,25), "F", 2, Arrays.asList("Taille", "Vertige","Poids","Fumeur"));
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.of(2000,12,25), "F", "Somewhere", "Some phone");
        PatientNote patientNote = new PatientNote(25,12, LocalDate.now(),"Taille");
        PatientNote patientNote2 = new PatientNote(25,12, LocalDate.now(),"Vertige");
        PatientNote patientNote3 = new PatientNote(25,12, LocalDate.now(),"Poids");
        PatientNote patientNote4 = new PatientNote(25,12, LocalDate.now(),"Fumeur");
        List<PatientNote> patientNoteList = Arrays.asList(patientNote,patientNote2,patientNote3,patientNote4);
        Mockito.when(patientInfosProxy.readPatientByFirstNameAndLastName(firstName,lastName)).thenReturn(patient);
        Mockito.when(notesHistoryProxy.readNotesHistoryByPatientId(patient.getId())).thenReturn(patientNoteList);

        //WHEN
        DiabeteRisk result = diabeteRiskService.generateRiskByFirstNameAndLastName(firstName,lastName);

        //THEN
        Mockito.verify(patientInfosProxy,Mockito.times(1)).readPatientByFirstNameAndLastName(firstName,lastName);
        Mockito.verify(notesHistoryProxy,Mockito.times(1)).readNotesHistoryByPatientId(patient.getId());
        System.out.println(diabeteRisk);
        System.out.println(result);
        Assertions.assertEquals(diabeteRisk.toString(),result.toString());

    }

    @Test
    public void testCalculateRiskWithNoRisk() {
        //GIVEN
        DiabeteRisk diabeteRisk = new DiabeteRisk(null, LocalDate.of(1970,12,25), "M", 0, Collections.emptyList());
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.of(1970,12,25), "M", "Somewhere", "Some phone");
        PatientNote patientNote = new PatientNote(25,12, LocalDate.now(),"Taille");
        PatientNote patientNote2 = new PatientNote(25,12, LocalDate.now(),"Vertige");
        List<PatientNote> patientNoteList = Arrays.asList(patientNote,patientNote2);

        //WHEN
        DiabeteRisk result = diabeteRiskService.calculateRisk(patient.getDateOfBirth(),patient.getGender(),Collections.emptyList());

        //THEN
        Assertions.assertEquals(diabeteRisk.toString(),result.toString());

    }

    @Test
    public void testCalculateRiskWithOlder30And6Terms() {
        //GIVEN
        DiabeteRisk diabeteRisk = new DiabeteRisk(null, LocalDate.of(1970,12,25), "M", 2, Arrays.asList("Taille", "Vertige","Poids","Fumeur","Rechute","Anticorps"));
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.of(1970,12,25), "M", "Somewhere", "Some phone");
        PatientNote patientNote = new PatientNote(25,12, LocalDate.now(),"Taille");
        PatientNote patientNote2 = new PatientNote(25,12, LocalDate.now(),"Vertige");
        PatientNote patientNote3 = new PatientNote(25,12, LocalDate.now(),"Poids");
        PatientNote patientNote4 = new PatientNote(25,12, LocalDate.now(),"Fumeur");
        PatientNote patientNote5 = new PatientNote(25,12, LocalDate.now(),"Rechute");
        PatientNote patientNote6 = new PatientNote(25,12, LocalDate.now(),"Anticorps");
        List<PatientNote> patientNoteList = Arrays.asList(patientNote,patientNote2,patientNote3,patientNote4,patientNote5,patientNote6);

        //WHEN
        DiabeteRisk result = diabeteRiskService.calculateRisk(patient.getDateOfBirth(),patient.getGender(),patientNoteList);

        //THEN
        Assertions.assertEquals(diabeteRisk.toString(),result.toString());

    }

    @Test
    public void testCalculateRiskWithYounger30And6Terms() {
        //GIVEN
        DiabeteRisk diabeteRisk = new DiabeteRisk(null, LocalDate.of(2000,12,25), "M", 3, Arrays.asList("Taille", "Vertige","Poids","Fumeur","Rechute","Anticorps"));
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.of(2000,12,25), "M", "Somewhere", "Some phone");
        PatientNote patientNote = new PatientNote(25,12, LocalDate.now(),"Taille");
        PatientNote patientNote2 = new PatientNote(25,12, LocalDate.now(),"Vertige");
        PatientNote patientNote3 = new PatientNote(25,12, LocalDate.now(),"Poids");
        PatientNote patientNote4 = new PatientNote(25,12, LocalDate.now(),"Fumeur");
        PatientNote patientNote5 = new PatientNote(25,12, LocalDate.now(),"Rechute");
        PatientNote patientNote6 = new PatientNote(25,12, LocalDate.now(),"Anticorps");
        List<PatientNote> patientNoteList = Arrays.asList(patientNote,patientNote2,patientNote3,patientNote4,patientNote5,patientNote6);

        //WHEN
        DiabeteRisk result = diabeteRiskService.calculateRisk(patient.getDateOfBirth(),patient.getGender(),patientNoteList);

        //THEN
        Assertions.assertEquals(diabeteRisk.toString(),result.toString());

    }
}
