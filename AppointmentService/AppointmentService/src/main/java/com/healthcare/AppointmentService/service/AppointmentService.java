package com.healthcare.AppointmentService.service;

import com.healthcare.AppointmentService.dto.AppointmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AppointmentService {
    public Boolean createAppoinment(AppointmentDto appointmentDto) {

        checkIfPatientExists(appointmentDto.getPatientId());
        checkIfDoctorSlotExists(appointmentDto.getDoctorId());
    return Boolean.TRUE;
    }

    private void checkIfDoctorSlotExists(UUID id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/getDoctorAvailability/" + id;
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("date","2025-01-01");
        URI uri1 = uri.build().toUri();

        ResponseEntity<String> response = restTemplate.getForEntity(uri1, String.class);
        System.out.println(response.getBody());

    }

    private Boolean checkIfPatientExists(UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/patient/" + id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody() != null;
    }
}
