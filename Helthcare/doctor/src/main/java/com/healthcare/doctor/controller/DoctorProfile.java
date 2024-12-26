package com.healthcare.doctor.controller;

import com.healthcare.doctor.dto.DoctorDto;
import com.healthcare.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class
DoctorProfile {
   @Autowired
   private DoctorService doctorService;

   @PostMapping("/doctor")
   private ResponseEntity<String> createDoctor(@RequestBody DoctorDto doctor)
   {
      UUID id =  doctorService.addDoctor(doctor);
       return  new ResponseEntity<>("doctor addes succesfully " + id , HttpStatus.OK);
   }

    @GetMapping("/doctor/{id}")
    private ResponseEntity<DoctorDto> getDoctor(@PathVariable  UUID id)
    {
       DoctorDto dto =  doctorService.getDoctor(id);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/doctor")
    private ResponseEntity<List<DoctorDto>> getDoctors()
    {
        List<DoctorDto> dto =  doctorService.getAllDoctors();
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/doctor/{id}")
    private Boolean updateDoctor(@PathVariable UUID id,@RequestBody DoctorDto doctorDto)
    {
         doctorService.updateDoctor(id,doctorDto);
         return  true;
    }





}
