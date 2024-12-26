package com.healthcare.AppointmentService.exception;



public class AppointmentNotAvilableException extends RuntimeException {

    public AppointmentNotAvilableException(String message){
        super(message);
    }

}
