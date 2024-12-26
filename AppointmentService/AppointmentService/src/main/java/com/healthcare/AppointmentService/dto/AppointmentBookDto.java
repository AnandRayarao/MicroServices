package com.healthcare.AppointmentService.dto;


import lombok.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentBookDto {
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


    private UUID doctorId;

    public Map<String, Boolean> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(Map<String, Boolean> timeSlots) {
        this.timeSlots = timeSlots;
    }

    private Map<String,Boolean> timeSlots;


}
