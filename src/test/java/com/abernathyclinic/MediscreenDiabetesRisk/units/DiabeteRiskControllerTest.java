package com.abernathyclinic.MediscreenDiabetesRisk.units;

import com.abernathyclinic.MediscreenDiabetesRisk.controllers.DiabeteRiskController;
import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.services.impl.DiabeteRiskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class DiabeteRiskControllerTest {

    @InjectMocks
    private DiabeteRiskController diabeteRiskController;

    @Mock
    private DiabeteRiskServiceImpl diabeteRiskService;

    @Test
    public void testGenerateDiabeteRiskById() {
        //GIVEN
        DiabeteRisk diabeteRisk = new DiabeteRisk(12, LocalDate.now(), "M", 2, Arrays.asList("Taille", "Vertige"));
        Mockito.when(diabeteRiskService.generateRiskById(diabeteRisk.getPatientId())).thenReturn(diabeteRisk);

        //WHEN
        ResponseEntity<Object> diabeteRiskById = diabeteRiskController.generateDiabeteRiskById(diabeteRisk.getPatientId());

        //THEN
        Mockito.verify(diabeteRiskService, Mockito.times(1)).generateRiskById(diabeteRisk.getPatientId());
        Assertions.assertEquals(diabeteRisk,diabeteRiskById.getBody());
        Assertions.assertEquals(HttpStatus.OK,diabeteRiskById.getStatusCode());

    }

    @Test
    public void testGenerateDiabeteRiskByIdWithError() {
        //GIVEN
        DiabeteRisk diabeteRisk = new DiabeteRisk(12, LocalDate.now(), "M", 2, Arrays.asList("Taille", "Vertige"));
        Mockito.when(diabeteRiskService.generateRiskById(diabeteRisk.getPatientId())).thenReturn(null);

        //WHEN
        ResponseEntity<Object> diabeteRiskById = diabeteRiskController.generateDiabeteRiskById(diabeteRisk.getPatientId());

        //THEN
        Mockito.verify(diabeteRiskService, Mockito.times(1)).generateRiskById(diabeteRisk.getPatientId());
        Assertions.assertEquals("Patient inconnu",diabeteRiskById.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,diabeteRiskById.getStatusCode());

    }

    @Test
    public void testGenerateDiabeteRiskByFirstNameAndLastName() {
        //GIVEN
        String firstName = "testFirstName";
        String lastName = "testLastName";
        DiabeteRisk diabeteRisk = new DiabeteRisk(12, LocalDate.now(), "M", 2, Arrays.asList("Taille", "Vertige"));
        Mockito.when(diabeteRiskService.generateRiskByFirstNameAndLastName(firstName,lastName)).thenReturn(diabeteRisk);

        //WHEN
        ResponseEntity<Object> diabeteRiskByFirstNameAndLastName = diabeteRiskController.generateDiabeteRiskByFirstNameAndLastName(firstName,lastName);

        //THEN
        Mockito.verify(diabeteRiskService, Mockito.times(1)).generateRiskByFirstNameAndLastName(firstName,lastName);
        Assertions.assertEquals(diabeteRisk,diabeteRiskByFirstNameAndLastName.getBody());
        Assertions.assertEquals(HttpStatus.OK,diabeteRiskByFirstNameAndLastName.getStatusCode());

    }

    @Test
    public void testGenerateDiabeteRiskByFirstNameAndLastNameWithError() {
        //GIVEN
        String firstName = "testFirstName";
        String lastName = "testLastName";
        DiabeteRisk diabeteRisk = new DiabeteRisk(12, LocalDate.now(), "M", 2, Arrays.asList("Taille", "Vertige"));
        Mockito.when(diabeteRiskService.generateRiskByFirstNameAndLastName(firstName,lastName)).thenReturn(null);

        //WHEN
        ResponseEntity<Object> diabeteRiskByFirstNameAndLastName = diabeteRiskController.generateDiabeteRiskByFirstNameAndLastName(firstName,lastName);

        //THEN
        Mockito.verify(diabeteRiskService, Mockito.times(1)).generateRiskByFirstNameAndLastName(firstName,lastName);
        Assertions.assertEquals("Patient inconnu",diabeteRiskByFirstNameAndLastName.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,diabeteRiskByFirstNameAndLastName.getStatusCode());

    }
}
