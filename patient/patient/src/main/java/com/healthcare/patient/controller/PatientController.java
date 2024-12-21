package com.healthcare.patient.controller;

import com.healthcare.patient.dto.PatientDto;
import com.healthcare.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient")
    private Boolean addPatient(@RequestBody PatientDto patientDto){

        return patientService.addPatient(patientDto);
    }

    @GetMapping("/patient/{id}")
    private ResponseEntity<PatientDto> getPatient(@PathVariable UUID id){

        return   new ResponseEntity<>(patientService.getPatient(id), HttpStatus.OK);
    }

    @PutMapping("/patient/{id}")
    private Boolean getPatient(@PathVariable UUID id,@RequestBody PatientDto patientDto){

        return patientService.updatePatient(id,patientDto);
    }

}
