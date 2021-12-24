package com.abernathyclinic.MediscreenDiabetesRisk.models;

public class PatientNote {

    private Integer noteId;

    private Integer patientId;

    private String practitionerNote;

    public PatientNote(Integer noteId, Integer patientId, String practitionerNote) {
        this.noteId = noteId;
        this.patientId = patientId;
        this.practitionerNote = practitionerNote;
    }

    public PatientNote(Integer patientId, String practitionerNote) {
        this.patientId = patientId;
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

    @Override
    public String toString() {
        return "PatientNote{" +
                "id=" + noteId +
                ", patientId=" + patientId +
                ", practitionerNotes='" + practitionerNote + '\'' +
                '}';
    }
}
