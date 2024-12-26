package com.healthcare.doctor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.doctor.dto.DoctorAvailabilityDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private DoctorAvailabilityService doctorAvailabilityService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "appointment-book", groupId = "appointment-group")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
        DoctorAvailabilityDto dto = objectMapper.readValue(record.value(),DoctorAvailabilityDto.class);
        doctorAvailabilityService.updateDoctorAvailability(dto.getDoctorId(),dto.getDate(),dto);
        System.out.println("Consumed message from topic 'appointment-book': " + record.value());
    }
}