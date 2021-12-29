package com.abernathyclinic.MediscreenDiabetesRisk.models;

import java.time.LocalDate;

public class PatientNote {

    private Integer noteId;

    private Integer patientId;

    private LocalDate dateOfCreation;

    private String practitionerNote;

    public PatientNote(Integer noteId, Integer patientId, LocalDate dateOfCreation, String practitionerNote) {
        this.noteId = noteId;
        this.patientId = patientId;
        this.dateOfCreation = dateOfCreation;
        this.practitionerNote = practitionerNote;
    }

    public PatientNote(Integer patientId, LocalDate dateOfCreation, String practitionerNote) {
        this.patientId = patientId;
        this.dateOfCreation = dateOfCreation;
        this.practitionerNote = practitionerNote;
    }

    public PatientNote() {
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPractitionerNote() {
        return practitionerNote;
    }

    public void setPractitionerNote(String practitionerNote) {
        this.practitionerNote = practitionerNote;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "PatientNote{" +
                "id=" + noteId +
                ", patientId=" + patientId +
                ", practitionerNotes='" + practitionerNote + '\'' +
                '}';
    }
}
