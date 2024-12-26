package com.healthcare.AppointmentService.controller;

import com.healthcare.AppointmentService.dto.AppointmentDto;
import com.healthcare.AppointmentService.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAsync
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    private Boolean createAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.createAppointment(appointmentDto);
        return Boolean.TRUE;

    }


}
