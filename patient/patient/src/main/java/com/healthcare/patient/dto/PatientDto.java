package com.healthcare.patient.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class PatientDto {

    private UUID id;
    private  String name;
    private  String email;
    private  String phone;
    private  String medicalHistory;
}
