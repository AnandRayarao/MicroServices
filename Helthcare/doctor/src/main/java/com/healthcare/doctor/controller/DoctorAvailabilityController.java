package com.healthcare.doctor.controller;

import com.healthcare.doctor.dto.DoctorAvailabilityDto;
import com.healthcare.doctor.service.DoctorAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class DoctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityService doctorAvailabilityService;

    @PostMapping("/doctorAvailability")
    private ResponseEntity<Boolean> postDoctorAvailability(@RequestBody DoctorAvailabilityDto doctorAvailabilityDto) {
    doctorAvailabilityService.addDoctorAvailability(doctorAvailabilityDto);
    return null;
    }
    @GetMapping("/getDoctorAvailability/{id}")
    private  ResponseEntity<DoctorAvailabilityDto> fetchDoctoravailability(@PathVariable  UUID id, @RequestParam  LocalDate date){

        return   new ResponseEntity<>(doctorAvailabilityService.fetchDoctoravailability(id,date), HttpStatus.OK);
    }

    @PutMapping("/updateTimeslots/{id}")
    private ResponseEntity<Boolean> updateTimeSlots(@PathVariable  UUID id, @RequestParam  LocalDate date,@RequestBody DoctorAvailabilityDto doctorAvailabilityDto){

        doctorAvailabilityService.updateDoctorAvailability(id,date,doctorAvailabilityDto);

        return  new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }


}
