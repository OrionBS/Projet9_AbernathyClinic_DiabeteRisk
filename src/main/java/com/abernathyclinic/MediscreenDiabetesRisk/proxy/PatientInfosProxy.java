package com.abernathyclinic.MediscreenDiabetesRisk.proxy;

import com.abernathyclinic.MediscreenDiabetesRisk.models.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${url.patientInfos}", name = "PatientInfos")
public interface PatientInfosProxy {

    /**
     * récupère les infos d'un patient en fonction de son id.
     * @param patientId l'identifiant unique du patient.
     * @return les infos du patient.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/id")
    Patient readPatientById(@RequestParam Integer patientId);

    /**
     * récupère les infos d'un patient en fonction de son prénom et son nom.
     * @param firstName le prénom du patient.
     * @param lastName le nom du patient.
     * @return les infos du patient.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/name")
    Patient readPatientByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName);

}
