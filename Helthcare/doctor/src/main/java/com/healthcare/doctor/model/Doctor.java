package com.healthcare.doctor.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "doctor")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public LocalDateTime getUpdate_dt() {
        return update_dt;
    }

    public void setUpdate_dt(LocalDateTime update_dt) {
        this.update_dt = update_dt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID doctorId;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "contact_details",columnDefinition = "TEXT")
    private  String contactDetails;

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
