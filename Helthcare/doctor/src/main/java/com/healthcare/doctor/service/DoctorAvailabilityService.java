package com.healthcare.doctor.service;

import com.healthcare.doctor.dto.DoctorAvailabilityDto;
import com.healthcare.doctor.exceptions.DoctorNotFoundException;
import com.healthcare.doctor.model.DoctorAvailability;
import com.healthcare.doctor.repository.DoctorAvailabilityRepository;
import com.healthcare.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorAvailabilityService {

    @Autowired
    private DoctorAvailabilityRepository  doctorAvailabilityRepository;

    @Autowired
    private  DoctorRepository doctorRepository;

    public void addDoctorAvailability(DoctorAvailabilityDto doctorAvailabilityDto) {

      DoctorAvailability doctorAvailability =  DoctorAvailability.builder().availability_id(doctorAvailabilityDto.getAvailabilityId())
                        .timeSlots(doctorAvailabilityDto.getTimeSlots())
              .doctorId(doctorAvailabilityDto.getDoctorId())
                                        .date(doctorAvailabilityDto.getDate())
              .date(doctorAvailabilityDto.getDate())
                                                .build();
        doctorAvailabilityRepository.save(doctorAvailability);
    }

    public DoctorAvailabilityDto fetchDoctoravailability(UUID id, LocalDate date) {

        Optional<DoctorAvailability> doctorAvailability = Optional.ofNullable(doctorAvailabilityRepository.findByDoctorIdAndDate(id, date).orElseThrow(() -> new DoctorNotFoundException("dd")));
        if(doctorAvailability.isPresent()){
          DoctorAvailabilityDto availability = DoctorAvailabilityDto.builder()
                  .availabilityId(doctorAvailability.get().getAvailability_id())
                  .timeSlots(doctorAvailability.get().getTimeSlots())
                  .date(doctorAvailability.get().getDate())
                  .doctorId(doctorAvailability.get().getDoctorId())
                  .build();
          return availability;
        }
        return  null;
    }


    public void updateDoctorAvailability(UUID id, LocalDate date, DoctorAvailabilityDto doctorAvailabilityDto) {
      Optional<DoctorAvailability> doctorAvailability = Optional.ofNullable(doctorAvailabilityRepository.findByDoctorIdAndDate(id, date).orElseThrow(() -> new DoctorNotFoundException("dd")));
      if(doctorAvailability.isPresent()){
          DoctorAvailability doctorAvailability1 = doctorAvailability.get();
          Map<String,Boolean> mp = doctorAvailability1.getTimeSlots();
          mp.putAll(doctorAvailabilityDto.getTimeSlots());
          doctorAvailability1.setTimeSlots(mp);
          doctorAvailabilityRepository.save(doctorAvailability1);



      }
    }
}
