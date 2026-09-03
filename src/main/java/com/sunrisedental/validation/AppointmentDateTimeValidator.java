package com.sunrisedental.validation;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDateTimeValidator {

    public String validate(LocalDate appointmentDate, LocalTime appointmentTime) {

        if (appointmentDate == null || appointmentTime == null) {
            return "Please select appointment date and time.";
        }

        AppointmentTimeValidationStrategy strategy = selectStrategy(appointmentDate);

        if (!strategy.isValid(appointmentDate, appointmentTime)) {
            return strategy.getInvalidMessage();
        }

        return null;
    }

    private AppointmentTimeValidationStrategy selectStrategy(LocalDate appointmentDate) {

        LocalDate today = LocalDate.now();

        if (appointmentDate.isBefore(today)) {
            return new PastDateStrategy();
        }

        if (appointmentDate.isEqual(today)) {
            return new TodayStrategy();
        }

        return new FutureDateStrategy();
    }
}