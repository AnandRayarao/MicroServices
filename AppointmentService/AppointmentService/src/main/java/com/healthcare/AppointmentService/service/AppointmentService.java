package com.healthcare.AppointmentService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.AppointmentService.dto.AppointmentDto;

import com.healthcare.AppointmentService.dto.DoctorAvailabilityDto;
import com.healthcare.AppointmentService.exception.AppointmentNotAvilableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AppointmentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Boolean createAppointment(AppointmentDto appointmentDto) {

     CompletableFuture<Boolean> task1 =  checkIfPatientExists(appointmentDto.getPatientId());
        CompletableFuture<DoctorAvailabilityDto> task2 =    checkIfDoctorSlotExists(appointmentDto.getDoctorId());

        Boolean isPatientExists = task1.join();
        DoctorAvailabilityDto dto = task2.join();
       if(dto.getTimeSlots() != null && !dto.getTimeSlots().isEmpty() && dto.getTimeSlots().containsKey(appointmentDto.getTimeSlots())&& dto.getTimeSlots().get(appointmentDto.getTimeSlots())) {
           kafkaProducerService.sendMessage(appointmentDto);
       }
       else {
           throw new AppointmentNotAvilableException("Appointment Not Available");
       }
    return Boolean.TRUE;
    }

    private CompletableFuture<DoctorAvailabilityDto> checkIfDoctorSlotExists(UUID id) {
        try {

            String url = "http://localhost:8082/getDoctorAvailability/" + id;
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("date", "2025-01-01");
            URI uri = uriBuilder.build().toUri();
            ResponseEntity<DoctorAvailabilityDto> response = restTemplate.getForEntity(uri, DoctorAvailabilityDto.class);
           return CompletableFuture.completedFuture(response.getBody());
        } catch (Exception e) {
            // Log exception details
            System.err.println("Error occurred while checking doctor slot availability: " + e.getMessage());
            e.printStackTrace();
        }

        // Return null if any exception occurs or response is not valid
        return null;
    }

    private CompletableFuture<Boolean> checkIfPatientExists(UUID id) {
        String url = "http://localhost:8081/patient/" + id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return CompletableFuture.completedFuture(response.getBody() != null);
    }
}
