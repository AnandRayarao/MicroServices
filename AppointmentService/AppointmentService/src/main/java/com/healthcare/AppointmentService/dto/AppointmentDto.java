package com.healthcare.AppointmentService.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private UUID patientId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public String getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(String timeSlots) {
        this.timeSlots = timeSlots;
    }

    private UUID doctorId;
    private String timeSlots;


}
