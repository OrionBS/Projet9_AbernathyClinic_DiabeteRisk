package com.abernathyclinic.MediscreenDiabetesRisk.controllers;

import com.abernathyclinic.MediscreenDiabetesRisk.models.DiabeteRisk;
import com.abernathyclinic.MediscreenDiabetesRisk.services.DiabeteRiskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/assess")
public class DiabeteRiskController {

    private final DiabeteRiskService diabeteRiskService;

    public DiabeteRiskController(DiabeteRiskService diabeteRiskService) {
        this.diabeteRiskService = diabeteRiskService;
    }

    @GetMapping(path = "/id")
    public ResponseEntity<Object> generateDiabeteRiskById(@RequestParam Integer patientId) {

        DiabeteRisk diabeteRisk = diabeteRiskService.generateRiskById(patientId);

        if (diabeteRisk == null) {
            return new ResponseEntity<>("Patient inconnu",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(diabeteRisk, HttpStatus.OK);
    }

    @GetMapping(path = "/name")
    public ResponseEntity<Object> generateDiabeteRiskByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {

        DiabeteRisk diabeteRisk = diabeteRiskService.generateRiskByFirstNameAndLastName(firstName,lastName);

        if (diabeteRisk == null) {
            return new ResponseEntity<>("Patient inconnu",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(diabeteRisk,HttpStatus.OK);
    }

}
