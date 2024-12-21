package com.healthcare.doctor.dto;
import lombok.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorAvailabilityDto {
    private UUID availabilityId;
    private UUID doctorId;
    private LocalDate date;
    private Map<String,Boolean> timeSlots;
}
