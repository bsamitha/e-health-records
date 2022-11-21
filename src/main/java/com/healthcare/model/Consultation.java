package com.healthcare.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Consultation {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String diagnosis;

    @NotEmpty
    private String bloodPressure;

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    @NotNull
    private Doctor doctor;

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Consultation(String diagnosis, String bloodPressure, Doctor doctor, Patient patient) {
        this.diagnosis = diagnosis;
        this.bloodPressure = bloodPressure;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Consultation() {
    }
}
