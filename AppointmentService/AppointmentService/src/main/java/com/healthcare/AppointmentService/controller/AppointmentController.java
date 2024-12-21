package com.healthcare.AppointmentService.controller;

import com.healthcare.AppointmentService.dto.AppointmentDto;
import com.healthcare.AppointmentService.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    private Boolean createAppointment(@RequestBody AppointmentDto appointmentDto){

        appointmentService.createAppoinment(appointmentDto);
        return Boolean.TRUE;

    }


}
