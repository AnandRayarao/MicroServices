package com.healthcare.AppointmentService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.healthcare.AppointmentService.dto.AppointmentBookDto;
import com.healthcare.AppointmentService.dto.AppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "appointment-book";

    @Autowired
    private ObjectMapper objectMapper;


    public void sendMessage(AppointmentDto message) {
        try {

            AppointmentBookDto dto = new AppointmentBookDto();
            dto.setDate(message.getDate());
            dto.setDoctorId(message.getDoctorId());
            dto.setTimeSlots(Map.of(
                    message.getTimeSlots(),Boolean.FALSE
            ));
            kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(dto));
            System.out.println("Message sent to topic " + TOPIC + ": " + message);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();

        }
    }

}
