package com.sunrisedental.validation;

import java.time.LocalDate;
import java.time.LocalTime;

public class TodayStrategy implements AppointmentTimeValidationStrategy {

    @Override
    public boolean isValid(LocalDate appointmentDate, LocalTime appointmentTime) {
        return !appointmentTime.isBefore(LocalTime.now());
    }

    @Override
    public String getInvalidMessage() {
        return "Appointment time cannot be in the past for today's date.";
    }
}