package com.healthcare.AppointmentService.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "patient_id")
    private UUID patientId;

    @Column(name = "doctor_id")
    private UUID doctorId;

    @Column(name = "time_slots")
    private String timeSlots;

    @Column(name = "create_dt",nullable = false,updatable = false)
    private LocalDateTime createDt;

    @Column(name="update_dt",nullable = false)
    private LocalDateTime update_dt;

    @PrePersist
    protected  void onCreate(){
        this.createDt = LocalDateTime.now();
        this.update_dt  = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.update_dt = LocalDateTime.now();
    }




}
