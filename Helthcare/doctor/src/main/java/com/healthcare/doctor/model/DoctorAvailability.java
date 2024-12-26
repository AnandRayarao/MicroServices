package com.healthcare.doctor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "doctor_availability")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID availability_id;

    @Column(nullable = false,name = "doctor_id")
    private UUID doctorId;

    @Column(nullable = false)
    private LocalDate date;


    @Column(columnDefinition = "TEXT",nullable = false)
    @Convert(converter = TimeSlotConverter.class)
    private Map<String,Boolean> timeSlots;

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
