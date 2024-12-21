package com.healthcare.patient.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private  String email;

    @Column(nullable = false)
    private  String phone;

    @Column(name = "medical_history", columnDefinition = "text")
    private  String medicalHistory;

    @Column(name = "create_dt",nullable = false,updatable = false)
    private LocalDateTime create_dt;

    @Column(name="update_dt",nullable = false)
    private LocalDateTime update_dt;

    @PrePersist
    protected  void onCreate(){
        this.create_dt = LocalDateTime.now();
        this.update_dt  = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.update_dt = LocalDateTime.now();
    }




}
