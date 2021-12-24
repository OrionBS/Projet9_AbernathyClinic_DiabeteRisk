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

    @GetMapping
    public ResponseEntity<Object> generateDiabeteRisk(@RequestParam Integer patientId) {

        DiabeteRisk diabeteRisk = diabeteRiskService.generateRiskById(patientId);

        return new ResponseEntity<>(diabeteRisk, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> generateDiabeteRiskByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {

        DiabeteRisk diabeteRisk = diabeteRiskService.generateRiskByFirstNameAndLastName(firstName,lastName);

        return new ResponseEntity<>(diabeteRisk,HttpStatus.OK);
    }

}
