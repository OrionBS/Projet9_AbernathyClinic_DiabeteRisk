package com.abernathyclinic.MediscreenDiabetesRisk.controllers;

import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.services.DiabeteRiskService;
import com.abernathyclinic.MediscreenDiabetesRisk.services.impl.DiabeteRiskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/assess")
public class DiabeteRiskController {

    private final DiabeteRiskService diabeteRiskService;
    private final Logger logger = LoggerFactory.getLogger(DiabeteRiskController.class);

    public DiabeteRiskController(DiabeteRiskService diabeteRiskService) {
        this.diabeteRiskService = diabeteRiskService;
    }

    /**
     * récupère le risque de diabète généré avec l'id du patient.
     * @param patientId l'identificateur unique du patient.
     * @return le risque de diabète.
     */
    @GetMapping(path = "/id")
    public ResponseEntity<Object> generateDiabeteRiskById(@RequestParam Integer patientId) {

        DiabeteRisk diabeteRisk = diabeteRiskService.generateRiskById(patientId);

        if (diabeteRisk == null) {
            logger.debug("Patient inconnu.");
            return new ResponseEntity<>("Patient inconnu",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(diabeteRisk, HttpStatus.OK);
    }

    /**
     * récupère le risque de diabète généré avec le prénom et nom du patient.
     * @param firstName le prénom du patient.
     * @param lastName le nom du patient.
     * @return le risque de diabète.
     */
    @GetMapping(path = "/name")
    public ResponseEntity<Object> generateDiabeteRiskByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {

        DiabeteRisk diabeteRisk = diabeteRiskService.generateRiskByFirstNameAndLastName(firstName,lastName);

        if (diabeteRisk == null) {
            logger.debug("Patient inconnu.");
            return new ResponseEntity<>("Patient inconnu",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(diabeteRisk,HttpStatus.OK);
    }

}
