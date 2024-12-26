package com.healthcare.AppointmentService.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorAvailabilityDto {
    private UUID availabilityId;

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public Map<String, Boolean> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(Map<String, Boolean> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UUID getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(UUID availabilityId) {
        this.availabilityId = availabilityId;
    }

    private UUID doctorId;
    private LocalDate date;
    private Map<String,Boolean> timeSlots;

}
