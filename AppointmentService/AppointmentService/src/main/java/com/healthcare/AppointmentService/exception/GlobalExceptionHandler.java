package com.healthcare.AppointmentService.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AppointmentNotAvilableException.class)
    public ResponseEntity<ErrorDetails> handleDocNotFound(AppointmentNotAvilableException ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>(errorDetails, headers, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ErrorDetails> handleGlobalException(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>(errorDetails, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
