package com.sunrisedental.validation;

import java.time.LocalDate;
import java.time.LocalTime;

public class FutureDateStrategy implements AppointmentTimeValidationStrategy {

    @Override
    public boolean isValid(LocalDate appointmentDate, LocalTime appointmentTime) {
        return true;
    }

    @Override
    public String getInvalidMessage() {
        return null;
    }
}