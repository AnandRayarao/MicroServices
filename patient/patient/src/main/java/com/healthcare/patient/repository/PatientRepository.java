package com.healthcare.patient.repository;

import com.healthcare.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient,UUID> {
}
