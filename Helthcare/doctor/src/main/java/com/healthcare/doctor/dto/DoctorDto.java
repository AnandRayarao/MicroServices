package com.healthcare.doctor.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class DoctorDto {
    private UUID id;
    private  String name;
    private String specialization;
    private String location;
    private String email;
    private String phone;
    private  String contactDetails;
}
