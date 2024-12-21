package com.healthcare.doctor.exceptions;

public class DoctorNotFoundException  extends RuntimeException{
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
