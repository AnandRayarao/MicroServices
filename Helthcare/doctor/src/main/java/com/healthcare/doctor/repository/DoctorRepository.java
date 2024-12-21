package com.healthcare.doctor.repository;

import com.healthcare.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface  DoctorRepository extends JpaRepository<Doctor, UUID> {

}
