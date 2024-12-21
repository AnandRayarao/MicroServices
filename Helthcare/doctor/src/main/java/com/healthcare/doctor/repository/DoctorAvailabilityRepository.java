package com.healthcare.doctor.repository;

import com.healthcare.doctor.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, UUID> {

    Optional<DoctorAvailability> findByDoctor_DoctorIdAndDate(UUID doctorId, LocalDate date);


}
