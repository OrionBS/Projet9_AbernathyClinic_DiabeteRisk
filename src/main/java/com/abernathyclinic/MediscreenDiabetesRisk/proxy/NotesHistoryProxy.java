package com.abernathyclinic.MediscreenDiabetesRisk.proxy;

import com.abernathyclinic.MediscreenDiabetesRisk.models.PatientNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(url = "${url.notesHistory}", name = "NotesHistory")
public interface NotesHistoryProxy {

    /**
     * récupère la liste de note du patient en fonction de son id.
     * @param patientId identifiant unique du patient.
     * @return la liste des ses notes.
     */
    @RequestMapping(method = RequestMethod.GET)
    List<PatientNote> readNotesHistoryByPatientId(@RequestParam Integer patientId);

}
