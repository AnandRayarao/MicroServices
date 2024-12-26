package com.healthcare.patient.service;

import com.healthcare.patient.dto.PatientDto;
import com.healthcare.patient.model.Patient;
import com.healthcare.patient.repository.PatientRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Boolean addPatient(PatientDto dto) {

        try {
            Patient patient = Patient.builder().
                    name(dto.getName())
                    .phone(dto.getPhone())
                    .email(dto.getEmail())
                    .medicalHistory(dto.getMedicalHistory())
                    .build();
            patientRepository.save(patient);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new ResourceNotFoundException("unable to add Patient", e);
        }
    }

    public PatientDto getPatient(UUID id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            Patient pt = patient.get();
            return PatientDto.builder()
                    .name(pt.getName())
                    .phone(pt.getPhone())
                    .email(pt.getEmail())
                    .medicalHistory(pt.getMedicalHistory())
                    .build();
        } else {
            throw new ResourceNotFoundException("Doctor not found");
        }

    }

    public Boolean updatePatient(UUID id, PatientDto patientDto) {

        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            patient.setEmail(patientDto.getEmail());
            return true;
        } else {
            throw new ResourceNotFoundException("Doctor not found");
        }


    }


    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> allPatients = patients.stream().map(a -> PatientDto.builder().id(a.getId())
                .name(a.getName())
                .phone(a.getPhone())
                .email(a.getEmail())
                .medicalHistory(a.getMedicalHistory())
                .build()
        ).toList();
        return allPatients;

    }
}

