package com.healthcare.doctor.service;


import com.healthcare.doctor.dto.DoctorDto;
import com.healthcare.doctor.exceptions.DoctorNotFoundException;
import com.healthcare.doctor.model.Doctor;
import com.healthcare.doctor.repository.DoctorRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    public UUID addDoctor(DoctorDto dto) {
        try {
            Doctor doctor = Doctor.builder().
                    name(dto.getName())
                    .phone(dto.getPhone())
                    .email(dto.getEmail())
                    .contactDetails(dto.getContactDetails())
                    .location(dto.getLocation())
                    .specialization(dto.getSpecialization())
                    .build();
            doctorRepository.save(doctor);
            return doctor.getDoctorId();
        } catch (Exception e) {
            throw new DoctorNotFoundException("exception while adding doctor");
        }
    }

    public DoctorDto getDoctor(UUID id) {
        return doctorRepository.findById(id).map(doctor ->
                        DoctorDto.builder().
                                name(doctor.getName())
                                .phone(doctor.getPhone())
                                .email(doctor.getEmail())
                                .contactDetails(doctor.getContactDetails())
                                .location(doctor.getLocation())
                                .specialization(doctor.getSpecialization())
                                .id(doctor.getDoctorId())
                                .build())
                .orElseThrow(() -> new DoctorNotFoundException("doctor not found"));
    }


    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(DoctorService::mapToDoctor)
                .toList();
    }

    private static DoctorDto mapToDoctor(Doctor doc) {
        return DoctorDto.builder().
                name(doc.getName())
                .phone(doc.getPhone())
                .email(doc.getEmail())
                .contactDetails(doc.getContactDetails())
                .location(doc.getLocation())
                .specialization(doc.getSpecialization())
                .id(doc.getDoctorId())
                .build();
    }

    public void updateDoctor(UUID uuid, DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.findById(uuid).orElse(null);
        if (null != doctor) {
            doctor.setEmail(doctorDto.getEmail());
            doctorRepository.save(doctor);
        }

    }
}
