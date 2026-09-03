package com.sunrisedental.validation;

import java.time.LocalDate;
import java.time.LocalTime;

public class PastDateStrategy implements AppointmentTimeValidationStrategy {

    @Override
    public boolean isValid(LocalDate appointmentDate, LocalTime appointmentTime) {
        return false;
    }

    @Override
    public String getInvalidMessage() {
        return "Appointment date cannot be in the past.";
    }
}